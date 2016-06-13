package com.hankil.app.mybatis.persistence;

import java.util.List;
import com.hankil.app.mybatis.bean.ProductCategoryVo;
import com.hankil.app.mybatis.bean.ProductVo;

public interface ProductMapper {

	public List<ProductCategoryVo> getProductCategoryList();
	public ProductCategoryVo getProductCategoryInfo(ProductCategoryVo productCategoryVo) throws Exception;
	public void addProductCategoryInfo(ProductCategoryVo productCategoryVo) throws Exception;
	public void modifyProductCategoryInfo(ProductCategoryVo productCategoryVo) throws Exception;
	public List<ProductVo> getProductList(ProductVo productVo);
	public ProductVo getProductInfo(ProductVo productVo) throws Exception;
	public void addProductInfo(ProductVo productVo) throws Exception;
	public void modifyProductInfo(ProductVo productVo) throws Exception;
}
