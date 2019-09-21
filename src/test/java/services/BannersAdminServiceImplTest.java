package services;

import boot.dao.BannerChangeDAO;
import boot.dao.BannerDAO;
import boot.model.Banner;
import boot.model.BannerChange;
import boot.services.BannersAdminServiceImpl;
import org.easymock.EasyMockRule;
import org.easymock.EasyMockSupport;
import org.easymock.TestSubject;
import org.junit.Rule;
import org.junit.Test;
import org.easymock.Mock;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.easymock.EasyMock.expect;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;



public class BannersAdminServiceImplTest extends EasyMockSupport
{
    @Rule
    public EasyMockRule em = new EasyMockRule(this);

    @TestSubject
    private BannersAdminServiceImpl testedObject = new BannersAdminServiceImpl();

    @Mock
    private BannerDAO bannerDAO;

    @Mock
    private BannerChangeDAO bannerChangeDAO;

    @Test
    public void testGetAllBanners()
    {
        List<Banner> listBanner= new ArrayList<>();
        listBanner.add(new Banner(2, "TEST", 2, 3,
                "TEST2", "TEST3"));
        expect(bannerDAO.getAllBanners()).andReturn(listBanner);
        replayAll();

        assertThat(testedObject.getAllBanners(), is(listBanner));
    }

    @Test
    public void testGetBannerIsNull()
    {
        expect(bannerDAO.getBanner(null)).andReturn(null);
        replayAll();
        assertNull(testedObject.getBanner(null));
    }

    @Test
    public void testGetBanner()
    {
        Banner expected = new Banner(1, "TEST", 0, 0,
                "TEST", "TEST");
        expect(bannerDAO.getBanner(1)).andReturn(expected);
        replayAll();

        assertThat(testedObject.getBanner(1), is(expected));
    }

    @Test
    public void testGetBannerNotExist()
    {
        expect(bannerDAO.getBanner(0)).andReturn(null);
        replayAll();
        assertNull(testedObject.getBanner(0));
    }

    @Test
    public void testGetAllBannersChanges()
    {
        BannerChange expected = new BannerChange(1, 1, 1,
                "CREATE", null, Timestamp.valueOf("2016-09-21 22:25:35").toLocalDateTime());

        List<BannerChange> actualList = new ArrayList<>();
        actualList.add(expected);
        expect(bannerChangeDAO.getAllBannersChanges()).andReturn(actualList);
        replayAll();

        assertThat(testedObject.getAllBannersChanges(), is(actualList));
    }

    @Test
    public void testGetBannersChanges()
    {
        BannerChange expected = new BannerChange(3, 1, 2,
                "DELETE", null, Timestamp.valueOf("2016-09-21 22:25:35").toLocalDateTime());

        List<BannerChange> actualList = new ArrayList<>();
        actualList.add(expected);
        expect(bannerChangeDAO.getBannersChanges(3, 1)).andReturn(actualList);
        replayAll();

        assertThat(testedObject.getBannersChanges(3, 1), is(actualList));
    }

    @Test
    public void testGetBannersChangesByBannerId()
    {
        BannerChange expected = new BannerChange(4, 5, 1,
                "CREATE", null, Timestamp.valueOf("2016-09-21 22:25:35").toLocalDateTime());

        List<BannerChange> actualList = new ArrayList<>();
        actualList.add(expected);
        expect(bannerChangeDAO.getBannersChanges(5, 2)).andReturn(actualList);
        replayAll();

        assertThat(testedObject.getBannersChanges(5, 2), is(actualList));
    }

    @Test
    public void testGetBannersChangesByAdminId()
    {
        BannerChange expected = new BannerChange(3, 1, 2,
                "DELETE", null, Timestamp.valueOf("2016-09-21 22:25:35").toLocalDateTime());

        List<BannerChange> actualList = new ArrayList<>();
        actualList.add(expected);
        expect(bannerChangeDAO.getBannersChanges(2, 3)).andReturn(actualList);
        replayAll();

        assertThat(testedObject.getBannersChanges(2, 3), is(actualList));
    }

    @Test
    public void testGetBannersChangesIsNull()
    {
        expect(bannerChangeDAO.getBannersChanges(null, 1)).andReturn(null);
        expect(bannerChangeDAO.getBannersChanges(null, null)).andReturn(null);
        expect(bannerChangeDAO.getBannersChanges(1, null)).andReturn(null);
        expect(bannerChangeDAO.getBannersChanges(1, 0)).andReturn(null);
        expect(bannerChangeDAO.getBannersChanges(1, 4)).andReturn(null);
        replayAll();
        assertNull(testedObject.getBannersChanges(null, 1));
        assertNull(testedObject.getBannersChanges(null, null));
        assertNull(testedObject.getBannersChanges(1, null));
        assertNull(testedObject.getBannersChanges(1, 0));
        assertNull(testedObject.getBannersChanges(1, 4));
    }

}
