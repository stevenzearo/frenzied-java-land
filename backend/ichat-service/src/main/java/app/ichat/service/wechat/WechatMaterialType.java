package app.ichat.service.wechat;

/**
 * @author Steve Zou
 */
public enum WechatMaterialType {
    news("news");

    private final String value;

    WechatMaterialType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
