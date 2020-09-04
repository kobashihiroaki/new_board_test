package test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import new_board.BoardDAO;
import new_board.BoardDTO;

public class BoardDAOTest {


//	@Test
//	public void testGetInstance() {
//		assertEquals(bdao, BoardDAO.getInstance());
//	}

//	@Test
//	public void testDeleteTopic() {
//		BoardDAO bdao = BoardDAO.getInstance();
//		BoardDTO bdto = new BoardDTO();
//		bdto.setId(7);
//		bdao.deleteTopic(bdto);
//
//	}


	private IDatabaseTester databaseTester;
	private IDatabaseConnection connection;

	public BoardDAOTest() throws Exception {
		//テストクラスをインスタンス化するときに、DBに接続するためのtesterを作成する
		databaseTester = new JdbcDatabaseTester("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost/board_test?serverTimezone=JST&useUnicode=true&characterEncoding=utf8", "root", "hiroaki");

	}

	@Before
	public void before() throws Exception {
		//テーブルに初期化用のデータを投入する
		IDataSet dataSet =
				new FlatXmlDataSetBuilder().build(new File("data/test_data.xml"));
		databaseTester.setDataSet(dataSet);
		databaseTester.setSetUpOperation(DatabaseOperation.REFRESH);

		databaseTester.onSetup();
	}

	@After
	public void after() throws Exception {
		databaseTester.setTearDownOperation(DatabaseOperation.NONE);
		databaseTester.onTearDown();
	}


//
//	@Test
//	public void testGetTopics() throws Exception {
////		IDataSet databaseDataSet = databaseTester.getConnection().createDataSet();
//		BoardDAO bdao = BoardDAO.getInstance();
//		List<BoardDTO> actualTable = bdao.getTopics();
//
//		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("data/expected2.xml"));
//		ITable expectedTable = expectedDataSet.getTable("board2");
//
//		Assertion.assertEquals(expectedTable, (ITable)actualTable);
//	}


	@Test
	public void testGetTopics() throws Exception {
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("data/test_data.xml"));
		ITable expectedTable = expectedDataSet.getTable("board2");

//		IDataSet databaseDataSet = databaseTester.getConnection().createDataSet();
//		ITable actualTable = databaseDataSet.getTable("board2");
		BoardDAO bdao = BoardDAO.getInstance();
		List <BoardDTO> topics = bdao.getTopics();
		ArrayList<String> detail = new ArrayList<String>();
		ArrayList<Integer> id = new ArrayList<Integer>();
		ArrayList<String> start_at = new ArrayList<String>();
		for (int i = 0; i < topics.size(); i++) {
			BoardDTO topic = topics.get(i);
			detail.add(topic.getDetail());
			id.add(topic.getId());
			start_at.add(topic.getStart_at());
		}
//		ITable filteredExpectedTable = DefaultColumnFilter.excludedColumnsTable(expectedTable, new String[]{"id", "start_at"});
//		ITable filteredActualTable = DefaultColumnFilter.excludedColumnsTable(actualTable, new String[]{"id", "start_at"});

		assertEquals(detail.get(0), "あああ!");
		assertNotNull(id);
		assertNotNull(start_at);

//		Assertion.assertEquals(filteredExpectedTable, getTopics());

//		ITable filteredId = DefaultColumnFilter.excludedColumnsTable(actualTable, new String[]{"detail", "start_at"});
//		ITable filteredStart_at = DefaultColumnFilter.excludedColumnsTable(actualTable, new String[]{"id", "detail"});

	}



	@Test
	public void testPostTopic() throws Exception {
		BoardDAO bdao = BoardDAO.getInstance();
		BoardDTO bdto = new BoardDTO();
		String str = "おーい";
		bdto.setDetail(str);
		bdao.postTopic(bdto);

		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("data/expected2.xml"));
		ITable expectedTable = expectedDataSet.getTable("board2");
		ITable filteredExpectedTable = DefaultColumnFilter.excludedColumnsTable(expectedTable, new String[]{"id", "start_at"});
		IDataSet databaseDataSet = databaseTester.getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("board2");
		ITable filteredActualTable = DefaultColumnFilter.excludedColumnsTable(actualTable, new String[]{"id", "start_at"});
		Assertion.assertEquals(filteredExpectedTable, filteredActualTable);

		ITable filteredId = DefaultColumnFilter.excludedColumnsTable(actualTable, new String[]{"detail", "start_at"});
		ITable filteredStart_at = DefaultColumnFilter.excludedColumnsTable(actualTable, new String[]{"id", "detail"});
		assertNotNull(filteredId);
		assertNotNull(filteredStart_at);
	}



	@Test
	public void testDeleteTopic() throws Exception {
		BoardDAO bdao = BoardDAO.getInstance();
		BoardDTO bdto = new BoardDTO();
		bdto.setId(4);
		bdao.deleteTopic(bdto);

		IDataSet databaseDataSet = databaseTester.getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("board2");
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("data/test_data.xml"));
		ITable expectedTable = expectedDataSet.getTable("board2");

		Assertion.assertEquals(expectedTable, actualTable);
	}








//@Test
//public void testGetTopics() throws Exception {
//	IDataSet databaseDataSet = databaseTester.getConnection().createDataSet();
//	ITable actualTable = databaseDataSet.getTable("board2");
//
//	IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("data/expected2.xml"));
//	ITable expectedTable = expectedDataSet.getTable("board2");
//
//	Assertion.assertEquals(expectedTable, actualTable);
//}


//
//@Test
//public void testPostTopic() throws Exception {
//
//	IDatabaseConnection testerConnection = databaseTester.getConnection();
//	Connection con = testerConnection.getConnection();
//	IDataSet databaseDataSet = databaseTester.getConnection().createDataSet();
//
//	Statement stmt = con.createStatement();
//	stmt.executeUpdate("insert into board2(id, detail, start_at) values('2', 'おーい', '2020-09-02 16:51:00')");
//
//	ITable actualTable = databaseDataSet.getTable("board2");
//	IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("data/expected2.xml"));
//	ITable expectedTable = expectedDataSet.getTable("board2");
//	Assertion.assertEquals(expectedTable, actualTable);
//}



//	@Test
//	public void testPostTopic() {
//		BoardDAO bdao = BoardDAO.getInstance();
//		BoardDTO bdto = new BoardDTO();
//
//		bdto.setDetail("おーい");
////		int aaa = bdao.postTopic(bdto);
//		List<BoardDTO> topics = bdao.getTopics();
//		BoardDTO topic = topics.get(topics.size() - 1);
//		assertEquals("おーい", topic.getDetail());
//	}
//
//	@Test
//	public void testGetTopics() {
//		BoardDAO bdao = BoardDAO.getInstance();
//		List<BoardDTO> topics = bdao.getTopics();
//		BoardDTO topic = topics.get(topics.size() - 1);
//		assertEquals("おーい", topic.getDetail());
//	}



}
