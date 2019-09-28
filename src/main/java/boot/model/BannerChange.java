package boot.model;




import java.time.LocalDate;
import java.util.Objects;

/**Класс для хранения данных о действии над интернет-баннером.
 @author Артемьев Р.А.
 @version 17.09.2019 */
public class BannerChange
{
    private Integer bannerChangeId;
    private Integer bannerId;
    private Integer adminId;
    private String  typeChange;
    private String   descriptionChange;
    private LocalDate dateChange;

    public BannerChange(Integer bannerChangeId, Integer bannerId, Integer adminId,
                        String typeChange, String descriptionChange, LocalDate dateChange)
    {
        this.bannerChangeId = bannerChangeId;
        this.bannerId = bannerId;
        this.adminId = adminId;
        this.typeChange = typeChange;
        this.descriptionChange = descriptionChange;
        this.dateChange = dateChange;
    }

    public Integer getBannerChangeId() {
        return bannerChangeId;
    }

    public void setBannerChangeId(Integer bannerChangeId) {
        this.bannerChangeId = bannerChangeId;
    }

    public Integer getBannerId() {
        return bannerId;
    }

    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getTypeChange() {
        return typeChange;
    }

    public void setTypeChange(String typeChange) {
        this.typeChange = typeChange;
    }

    public String getDescriptionChange() {
        return descriptionChange;
    }

    public void setDescriptionChange(String descriptionChange) {
        this.descriptionChange = descriptionChange;
    }

    public LocalDate getDateChange() {
        return dateChange;
    }

    public void setDateChange(LocalDate dateChange) {
        this.dateChange = dateChange;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BannerChange that = (BannerChange) o;
        return Objects.equals(bannerChangeId, that.bannerChangeId) &&
                Objects.equals(bannerId, that.bannerId) &&
                Objects.equals(adminId, that.adminId) &&
                Objects.equals(typeChange, that.typeChange) &&
                Objects.equals(descriptionChange, that.descriptionChange) &&
                Objects.equals(dateChange, that.dateChange);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bannerChangeId, bannerId, adminId, typeChange, descriptionChange, dateChange);
    }

    @Override
    public String toString() {
        return "BannerChange{" +
                "bannerChangeId=" + bannerChangeId +
                ", bannerId=" + bannerId +
                ", adminId=" + adminId +
                ", typeChange='" + typeChange + '\'' +
                ", descriptionChange='" + descriptionChange + '\'' +
                ", dateChange=" + dateChange +
                '}';
    }
}

