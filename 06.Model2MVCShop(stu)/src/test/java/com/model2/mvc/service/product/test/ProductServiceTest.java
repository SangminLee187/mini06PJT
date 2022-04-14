package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;


/*
 *	FileName :  UserServiceTest.java
 * ㅇ JUnit4 (Test Framework) 과 Spring Framework 통합 Test( Unit Test)
 * ㅇ Spring 은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성 할 수 있다.
 * ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
 * ㅇ @ContextConfiguration : Meta-data location 지정
 * ㅇ @Test : 테스트 실행 소스 지정
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })

@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })
public class ProductServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	@Test
	public void testAddProduct() throws Exception {
		
		Product prod = new Product();
		prod.setProdNo(77777);
		prod.setProdName("쏠라씨");
		prod.setProdDetail("디테일");
		prod.setManuDate("220407");
		prod.setPrice(88888);
		prod.setFileName("파일이름");
		
		productService.addProduct(prod);
		
		prod = productService.getProduct(prod.getProdNo());
		
		//==> console 확인
		System.out.println(prod);
		
		//==> API 확인

		Assert.assertEquals("쏠라씨", prod.getProdName());
		Assert.assertEquals("디테일", prod.getProdDetail());
		Assert.assertEquals("220407", prod.getManuDate());
		Assert.assertEquals(88888, prod.getPrice());
		Assert.assertEquals("파일이름", prod.getFileName());

	}
	
	//@Test
	public void testGetProduct() throws Exception {
		
		Product prod = new Product();

//			###업데이트용 데이터###
//		prod.setProdNo(77777);
//		prod.setProdName("쏠라씨");
//		prod.setProdDetail("디테일");
//		prod.setManuDate("220407");
//		prod.setPrice(88888);
//		prod.setFileName("파일이름");
		
		prod = productService.getProduct(77777);

		//==> console 확인
		//System.out.println(user);
		
		//==> API 확인
		Assert.assertEquals("쏠라씨", prod.getProdName());
		Assert.assertEquals("디테일", prod.getProdDetail());
		Assert.assertEquals("220407", prod.getManuDate());
		Assert.assertEquals(88888, prod.getPrice());
		Assert.assertEquals("파일이름", prod.getFileName());

//		Assert.assertNotNull(productService.getProduct(11111));
//		Assert.assertNotNull(productService.getProduct(33333));
		Assert.assertNotNull(productService.getProduct(77777));

	}
	
	//@Test
	 public void testUpdateProduct() throws Exception{
		 
		Product prod = productService.getProduct(77777);
		Assert.assertNotNull(prod);
		
		Assert.assertEquals("쏠라씨", prod.getProdName());
		Assert.assertEquals("디테일", prod.getProdDetail());
		Assert.assertEquals("220407", prod.getManuDate());
		Assert.assertEquals(88888, prod.getPrice());
		Assert.assertEquals("파일이름", prod.getFileName());

		prod.setProdName("쏠라씨77");
		prod.setProdDetail("디테일77");
		prod.setManuDate("220408");
		prod.setPrice(77777);
		prod.setFileName("파일이름77");
		
		productService.updateProduct(prod);
		
		prod = productService.getProduct(77777);
		Assert.assertNotNull(prod);
		
		//==> console 확인
		//System.out.println(user);
			
		//==> API 확인
		Assert.assertEquals("쏠라씨77", prod.getProdName());
		Assert.assertEquals("디테일77", prod.getProdDetail());
		Assert.assertEquals("220408", prod.getManuDate());
		Assert.assertEquals(77777, prod.getPrice());
		Assert.assertEquals("파일이름77", prod.getFileName());
	 }

	
	 //==>  주석을 풀고 실행하면....
//	 @Test
	 public void testGetProductListAll() throws Exception{
System.out.println("===== getProductListAll START =====");

	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(18, list.size());
	 	
		//==> console 확인
	 	//System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
System.out.println(totalCount);
	 		 	
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("");
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(18, list.size());
	 	
	 	//==> console 확인
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
System.out.println(totalCount);
System.out.println("===== getProductListAll END =====");
	 }
	 
//	 @Test
	 public void testGetProductListByProductNo() throws Exception{
System.out.println("===== getProductList prodNo START =====");

	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("77777");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 		 	
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
System.out.println("===== getProductList prodNo END =====");
	 }
	 
//	 @Test
	 public void testGetProductListByProductName() throws Exception{
System.out.println("===== getProductList prodName START =====");

	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword("쏠라씨");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 		 	
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
System.out.println("===== getProductList prodName END =====");
	 }

	 
//	 	 @Test
	 public void testGetProductListByPrice() throws Exception{
System.out.println("===== getProductList Price START =====");

	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("2");
	 	search.setSearchKeyword("88888");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 		 	
	 	search.setSearchCondition("2");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
System.out.println("===== getProductList Price END =====");
	 }
}