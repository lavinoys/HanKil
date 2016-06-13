package com.hankil.app.service;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hankil.app.mybatis.bean.ProductCategoryVo;
import com.hankil.app.mybatis.bean.ProductVo;
import com.hankil.app.mybatis.persistence.ProductMapper;

@Service
public class ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	private ProductMapper productMapper;
	
	public List<ProductCategoryVo> getProductCategoryList(){
		return productMapper.getProductCategoryList();
	}
	
	public ProductCategoryVo getProductCategoryInfo(ProductCategoryVo productCategoryVo){
		try{
			productCategoryVo = productMapper.getProductCategoryInfo(productCategoryVo);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return productCategoryVo;
	}
	
	public void addProductCategoryInfo(ProductCategoryVo productCategoryVo){
		try{
			productMapper.addProductCategoryInfo(productCategoryVo);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
	}
	public void modifyProductCategoryInfo(ProductCategoryVo productCategoryVo){
		try{
			productMapper.modifyProductCategoryInfo(productCategoryVo);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
	}
	public List<ProductVo> getProductList(ProductVo productVo){
		return productMapper.getProductList(productVo);
	}
	public ProductVo getProductInfo(ProductVo productVo){
		try{
			productVo = productMapper.getProductInfo(productVo);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return productVo;
	}
	
	public void addProductInfo(ProductVo productVo){
		try{
			productMapper.addProductInfo(productVo);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
	}
	public void modifyProductInfo(ProductVo productVo){
		try{
			productMapper.modifyProductInfo(productVo);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
	}
}
