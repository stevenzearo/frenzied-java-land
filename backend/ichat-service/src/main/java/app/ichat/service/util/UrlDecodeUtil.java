package app.ichat.service.util;

import app.web.error.ConflictException;
import okhttp3.HttpUrl;

/**
 * @author Steve Zou
 */
public final class UrlDecodeUtil {
    public static HttpUrl.Builder toHttpUrlBuilder(String url) {
        if (!url.startsWith("http") && !url.startsWith("https")) {
            throw new ConflictException("invalid http url, is not start with `http` or `https`, " + url);
        }
        String[] split = url.split("://");
        if (split.length > 2) {
            throw new ConflictException("invalid http url， contains too much `://`, url=" + url);
        }
        String schema = split[0];
        String restPart = split[1];
        String[] restSplit = restPart.split("/");
        String domain = restSplit[0];
        HttpUrl.Builder builder = new HttpUrl.Builder();
        builder.scheme(schema);
        builder.host(domain);
        for (int i = 1; i < restSplit.length; i++) {
            builder.addPathSegment(restSplit[i]);
        }
        return builder;
    }
}
