package test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import new_board.BoardDTO;

public class BoardDTOTest {
	BoardDTO bdto = new BoardDTO();
	@Test
	public void testGetId() {
		bdto.setId(1);
		assertEquals(1, bdto.getId());
	}

	@Test
	public void testSetId() {
		bdto.setId(1);
		assertEquals(1, bdto.getId());
	}

	@Test
	public void testGetDetail() {
		bdto.setDetail("あれれ？");
		assertEquals("あれれ？", bdto.getDetail());
	}

	@Test
	public void testSetDetail() {
		bdto.setDetail("あれれ？");
		assertNotNull(bdto.getDetail());
	}

	@Test
	public void testGetStart_at() {
		String strDate = "2020/09/01 15:45:00";
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date start_at = null;
		try {
			start_at = df.parse(strDate);
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		bdto.setStart_at(start_at);
		assertEquals("2020/09/01 15:45:00", bdto.getStart_at());
	}

	@Test
	public void testSetStart_at() {
		String strDate = "2020/09/01 15:45:00";
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date start_at = null;
		try {
			start_at = df.parse(strDate);
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		bdto.setStart_at(start_at);
		assertNotNull(bdto.getStart_at());
	}

}
