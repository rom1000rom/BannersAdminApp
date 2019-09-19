package boot.model;

import java.util.Objects;

/**Класс для хранения данных об интернет-баннере.
 @author Артемьев Р.А.
 @version 13.09.2019 */
public class Banner
{
    private Integer bannerId;
    private String imgSrc;
    private Integer width;
    private Integer height;
    private String targetUrl;
    private String langId;

    public Banner(Integer bannerId, String imgSrc, Integer width,
                  Integer height, String targetUrl, String langId)
    {
        this.bannerId = bannerId;
        this.imgSrc = imgSrc;
        this.width = width;
        this.height = height;
        this.targetUrl = targetUrl;
        this.langId = langId;
    }

    public Integer getBannerId() {
        return bannerId;
    }

    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getLangId() {
        return langId;
    }

    public void setLangId(String langId) {
        this.langId = langId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Banner banner = (Banner) o;
        return bannerId.equals(banner.bannerId) &&
                imgSrc.equals(banner.imgSrc) &&
                width.equals(banner.width) &&
                height.equals(banner.height) &&
                targetUrl.equals(banner.targetUrl) &&
                langId.equals(banner.langId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bannerId, imgSrc, width, height, targetUrl, langId);
    }

    @Override
    public String toString()
    {
        return "Banner{" +
                "bannerId=" + bannerId +
                ", imgSrc='" + imgSrc + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", targetUrl='" + targetUrl + '\'' +
                ", langId='" + langId + '\'' +
                '}';
    }
}
