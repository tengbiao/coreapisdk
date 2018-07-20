package ts.coreapi.sdk;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ts.coreapi.sdk.model.*;

public class CoreAPIClient {
	private String[] apiHost;
	private String appId;
	private String appKey;
	private SiteEnum siteEnum;
	private Gson gson;
	public static final String CHARSET = "UTF-8";

	/**
	 * 实例化CoreApiClient
	 * 
	 * @param appId
	 * @param appKey
	 * @param site
	 * @param apiHost
	 */
	public CoreAPIClient(String appId, String appKey, SiteEnum site, String... apiHost) {
		// TODO Auto-generated constructor stub
		this.appId = appId;
		this.appKey = appKey;
		this.siteEnum = site;
		this.apiHost = apiHost;
		this.gson = new GsonBuilder().enableComplexMapKeySerialization()// 支持Map的key为复杂对象的形式
				.serializeNulls() // 智能null,支持输出值为null的属性
				.setPrettyPrinting()// 格式化输出（序列化）
				.setDateFormat("yyyy-MM-dd HH:mm:ss") // 序列化日期格式化输出
				// .excludeFieldsWithoutExposeAnnotation() // 不序列化与反序列化没有@Expose标注的字段
				.disableHtmlEscaping() // 默认是GSON把HTML转义的
				.create();
	}

	/**
	 * HmacSha256 加密
	 * 
	 * @param inputStr
	 * @param key
	 * @return
	 */
	private String hmacSha256(String inputStr, String key) {
		try {
			Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
			SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(), "HmacSHA256");
			sha256_HMAC.init(secret_key);
			return Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(inputStr.getBytes(CHARSET)));
		} catch (Exception e) {
			System.out.println("Error");
		}
		return null;
	}

	/**
	 * 
	 * @param ins
	 * @return
	 * @throws IOException
	 */
	private final String readContent(InputStream ins) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(ins, CHARSET));
		if (ins != null) {
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		}
		return sb.toString();
	}

	/**
	 * 签名
	 * 
	 * @param request
	 */
	private void signRequest(HttpURLConnection request) {
		URL url = request.getURL();
		// 请求的host
		String host = url.getHost();
		String sanitizedHost = (host.indexOf(':') > 0) ? host.substring(0, host.indexOf(':')) : host;
		// 随机字符串
		String nonce = UUID.randomUUID().toString().substring(0, 6);
		// 获取当前时间戳
		long ts = System.currentTimeMillis() / 1000;
		String pathQuery = url.getPath() + (url.getQuery() == null ? "" : "?" + url.getQuery());
		String normalized = "hawk.1.header\n" + ts + "\n" + nonce + "\n" + request.getRequestMethod().toUpperCase()
				+ "\n" + pathQuery + "\n" + sanitizedHost + "\n" + (url.getPort() > 0 ? url.getPort() : 80) + "\n"
				+ "\n\n";
		String mscStr = hmacSha256(normalized, appKey);
		String authorization = "id=\"" + appId + "\",ts=\"" + ts + "\",nonce=\"" + nonce + "\",mac=\"" + mscStr + "\"";
		request.setRequestProperty("Authorization", "Hawk " + authorization);
	}

	/**
	 * post 请求
	 * 
	 * @param url
	 * @param data
	 * @param type
	 * @return
	 */
	private <T> T postRequest(String url, Object data, Type type) {
		HttpURLConnection con = null;
		DataOutputStream dos = null;
		InputStream ins = null;
		// InputStream ins = null;
		try {
			con = (HttpURLConnection) new URL(url).openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-type", "application/json; charset=utf-8");
			con.setRequestProperty("site", String.valueOf(siteEnum.getSite()));
			con.setRequestProperty("Accept", "application/json");
			con.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0");
			signRequest(con);
			con.setDoOutput(true);
			con.setUseCaches(false);
			if (data != null) {
				con.setDoInput(true);
				// 建立输入流，向指向的URL传入参数
				byte[] datas = gson.toJson(data).getBytes(CHARSET);
				con.setRequestProperty("Content-Length", String.valueOf(datas.length));
				dos = new DataOutputStream(con.getOutputStream());
				dos.write(datas);
				dos.flush();
			}
			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				ins = con.getInputStream();
				return gson.fromJson(readContent(ins), type);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (dos != null)
					dos.close();
				if (ins != null)
					ins.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * get 请求
	 * 
	 * @param url
	 * @param type
	 * @return
	 */
	private <T> T getRequest(String url, Type type) {
		HttpURLConnection con = null;
		InputStream ins = null;
		try {
			con = (HttpURLConnection) new URL(url).openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-type", "application/json; charset=utf-8");
			con.setRequestProperty("Accept", "text/json");
			con.setRequestProperty("site", String.valueOf(siteEnum.getSite()));
			con.setRequestProperty("User-agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0");
			signRequest(con);
			con.setDoOutput(true);
			con.setUseCaches(false);
			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				ins = con.getInputStream();
				return gson.fromJson(readContent(ins), type);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ins != null)
					ins.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 按照数组顺序替换字符串中的{0},{1}
	 * 
	 * @param input
	 * @param arg
	 * @return
	 */
	private String stringFormat(String input, Object... arg) {
		if (arg != null) {
			for (int i = 0; i < arg.length; i++) {
				input = input.replace("{" + i + "}", String.valueOf(arg[i]));
			}
		}
		return input;
	}

	/**
	 * 组合请求URL
	 * 
	 * @param path
	 * @return
	 */
	private String generateUrl(String path) {
		Random random = new Random();
		String baseUrl = apiHost[random.nextInt(apiHost.length)];
		if (baseUrl.endsWith("/"))
			return baseUrl + path;
		return baseUrl + "/" + path;
	}

	/**
	 * 获取在线客服链接
	 * 
	 * @return
	 */
	public ApiResultListData<OnlineCustomServiceResModel> getOnlineCustomService() {
		String url = stringFormat(generateUrl("api/Common/GetOnlineCustomService?site={0}"), siteEnum.getSite());
		return getRequest(url, new TypeToken<ApiResultListData<OnlineCustomServiceResModel>>() {
		}.getType());
	}

	/**
	 * 获取用户信息， 目前只有获取用户是否存款
	 * 
	 * @param user
	 * @return
	 */
	public ApiResultData<MemberInfoResModel> getMemberInfo(String user) {
		String url = stringFormat(generateUrl("api/Member/GetMemberInfo?site={0}&user={1}"), siteEnum.getSite(), user);
		Type type = new TypeToken<ApiResultData<MemberInfoResModel>>() {
		}.getType();
		return getRequest(url, type);
	}

	/**
	 * 获取备用网址
	 * 
	 * @param type
	 * @return
	 */
	public ApiResultListData<BackupLinkResModel> getBackupLink(BackupLinkType type) {
		String url = stringFormat(generateUrl("api/WebsiteUrl/GetBackupLink?site={0}&type={1}"), siteEnum.getSite(),
				type.getValue());
		return getRequest(url, new TypeToken<ApiResultListData<BackupLinkResModel>>() {
		}.getType());
	}

	/**
	 * 查询保修工具列表
	 * 
	 * @param type
	 * @return
	 */
	public ApiResultListData<ToolsRepairResModel> getToolsRepair(RepairType type) {
		String url = stringFormat(generateUrl("api/TechnicalSupport/GetToolsRepair?site={0}&type={1}"),
				siteEnum.getSite(), type.getValue());
		return getRequest(url, new TypeToken<ApiResultListData<ToolsRepairResModel>>() {
		}.getType());
	}

	/**
	 * 查询 客服APP，游戏APP等二维码
	 * 
	 * @param type
	 * @return
	 */
	public ApiResultData<String> getQRImage(QRImageType type) {
		String url = stringFormat(generateUrl("api/TechnicalSupport/GetQRImage?site={0}&type={1}"), siteEnum.getSite(),
				type.getValue());
		return getRequest(url, new TypeToken<ApiResultData<String>>() {
		}.getType());
	}

	/**
	 * 查询报修单列表
	 * 
	 * @param model
	 * @return
	 */
	public ApiResultListData<QueryRepairResModel> queryRepair(QueryRepairReqModel model) {
		return postRequest(generateUrl("api/GameAssistant/QueryRepair"), model,
				new TypeToken<ApiResultListData<QueryRepairResModel>>() {
				}.getType());
	}

	/**
	 * 添加报修单
	 * 
	 * @param model
	 * @return
	 */
	public ApiResult addRepair(AddRepairReqModel model) {
		return postRequest(generateUrl("api/GameAssistant/AddRepair"), model, new TypeToken<ApiResult>() {
		}.getType());
	}

	/**
	 * 获取游戏助手客户端的版本号
	 * 
	 * @return
	 */
	public ApiResultData<CheckVersionResModel> CheckVersion() {
		return getRequest(generateUrl("api/GameAssistant/CheckVersion"),
				new TypeToken<ApiResultData<CheckVersionResModel>>() {
				}.getType());
	}
}
