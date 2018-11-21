package axIoT.syscom;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * @Package Name   : axIoT.dao
 * @FileName  : CommonDao.java
 * @작성자       : ywkim
 * @프로그램 설명 : 공통 DAO
 */
@SuppressWarnings({"unchecked"})
public class CommonDao extends EgovAbstractDAO {
    private SqlMapClient sqlMapClient;
    
    /**
     * SELECT 쿼리를 실행하고 결과를 List로 반환한다.
     *
     * @param queryId 실행할 Query의 ID
     * @return 조회된 목록 데이터
     * @throws SQLException 
     */
    public <E> List<E> selectList(String queryId) throws SQLException {                     
        return (List<E>) sqlMapClient.queryForList(queryId);
    }

    /**
     * SELECT 쿼리를 실행하고 결과를 List로 반환한다.
     *
     * @param queryId 실행할 Query의 ID
     * @param parameter Input parameter 객체
     * @param rowBounds 특정 범위의 데이터만 조회하기 위한 범위 설정 객체
     * @return 조회된 목록 데이터
     */
    public <E> List<E> selectList(String queryId, Object parameter) throws SQLException {
        return (List<E>) sqlMapClient.queryForList(queryId, parameter);
    }
}
