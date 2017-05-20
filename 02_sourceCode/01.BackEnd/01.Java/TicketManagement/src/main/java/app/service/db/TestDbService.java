package app.service.db;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import app.constant.TicketConstants;

@Service
public class TestDbService {
	private static Logger log = LoggerFactory.getLogger(TestDbService.class);

    public List<Map<String, Object>> home() {
        String resource = TicketConstants.XML_MYBATIS_SETTING;
        try (Reader in = Resources.getResourceAsReader(resource)) {
            // ★設定ファイルを元に、 SqlSessionFactory を作成する
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);

            // ★SqlSessionFactory から SqlSession を生成する
            SqlSession session = factory.openSession();

            // ★SqlSession を使って SQL を実行する
            List<Map<String, Object>> result = session.selectList("sample.mybatis.selectTest");
            session.close();
            return result;
        } catch (IOException e) {
            // nop
        	e.printStackTrace();
        }

        return null;
    }

}
