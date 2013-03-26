package com.mtea.msdre.byspring.repo;

import junit.framework.Assert;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/StringStringRepositoryTest-context.xml"})
public class StringStringRepositoryTest {
	
	@Autowired StringStringRepository repo;
	
	@Before
	public void setUp() {
		repo.add("foo", "bar");
	}

	@Test
	public void shouldFindValue() {
		String value = repo.getValue("foo");
		
		Assert.assertNotNull("Value is <null>", value);
		Assert.assertEquals( "Value mismatch" , "bar", value);
	}
	
	@Test
	public void testAdd() {
		repo.add("name", "macrotea");
		String name = repo.getValue("name");
		Assert.assertEquals( "name mismatch" , "macrotea", name);
	}
	
	@Test
	public void testAdd2() {
		repo.add("name2", "macrotea2");
		String name = repo.getValue("name2");
		Assert.assertEquals( "name2 mismatch" , "macrotea2", name);
	}
	
	@Test
	public void testExists() {
		String ranKey = RandomStringUtils.randomAlphabetic(20);
		boolean flag = repo.exists(ranKey);
		Assert.assertFalse(flag);
		repo.add(ranKey, "ooooooooooo");
		flag = repo.exists(ranKey);
		Assert.assertTrue(flag);
	}
	
	@Test
	public void getType() {
		repo.add("xxxxx", "ooooooooooo");
		DataType tp = repo.getType("xxxxx");
		Assert.assertEquals("string", tp.code());
	}
	
}
