package ServiceTest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.se2project.covidtracker.model.News;
import service.NewsService;

class NewsTest {
private NewsService service;
private List<News> expectedNewsList;
private List<News> actualNewsList;
	@BeforeEach
	void setUp() throws Exception {
		service = new NewsService();
	}

	@Test
	void testAutoUpdateNews() throws NumberFormatException, SQLException, IOException, ParseException {
		/* assertEquals(true, service.autoUpdateNews()); */
		
	}
	
	@Test
	void testSelectAllNews() throws SQLException {
		expectedNewsList = new ArrayList<>();
		expectedNewsList.add(new News(1, "Phấn đấu thực hiện cao nhất mục tiêu phát triển kinh tế - xã hội trong bối cảnh dịch COVID-19","/phan-dau-thuc-hien-cao-nhat-muc-tieu-phat-trien-kinh-te-xa-hoi-trong-boi-canh-dich-covid-19/r/35212709.epi", "https://photo-3-baomoi.zadn.vn/w300_r3x2/2020_05_29_294_35212709/eeb4fbd1ab9242cc1b83.jpg", "Tin Tức TTXVN", "2020-05-29 20:14"));
		expectedNewsList.add(new News(2, "Chính sách nhập, xuất cảnh đối với người nước ngoài đảm bảo yêu cầu về phòng, chống dịch COVID-19", "/chinh-sach-nhap-xuat-canh-doi-voi-nguoi-nuoc-ngoai-dam-bao-yeu-cau-ve-phong-chong-dich-covid-19/r/35212646.epi", "https://photo-3-baomoi.zadn.vn/w300_r3x2/2020_05_29_294_35212646/80c090a4c0e729b970f6.jpg", "Tin Tức TTXVN", "2020-05-29 19:59"));
		expectedNewsList.add(new News(3, "Cập nhật Danh sách bệnh nhân COVID-19", "/cap-nhat-danh-sach-benh-nhan-covid-19/r/34560015.epi", "https://photo-1-baomoi.zadn.vn/w300_r3x2/2020_05_04_94_34560015/549a8c628c21657f3c30.jpg", "SK&ĐS", "2020-05-04 14:03"));
		actualNewsList = service.selectAllNews();
		for(int i = 0; i < expectedNewsList.size(); i++) {
			assertEquals(expectedNewsList.get(i).getId(), actualNewsList.get(i).getId());
			assertEquals(expectedNewsList.get(i).getTitle(), actualNewsList.get(i).getTitle());
			assertEquals(expectedNewsList.get(i).getUrl(), actualNewsList.get(i).getUrl());
			assertEquals(expectedNewsList.get(i).getImageUrl(), actualNewsList.get(i).getImageUrl());
			assertEquals(expectedNewsList.get(i).getSourceMeta(), actualNewsList.get(i).getSourceMeta());
			
		}
	}
	@Test
	void testDeleteAllNews() throws SQLException {
		/* assertEquals(true, service.deleteAllNews()); */
	}
	
	@Test
	void testResetNewsId() throws SQLException {
		assertEquals(true, service.resetNewsId());
	}

}
