package com.ecommerce.services;

import com.ecommerce.dtos.ProductInfo;
import com.ecommerce.model.Product;
import com.ecommerce.repositories.ProductRepository;
import com.ecommerce.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTestSuite {
    static ProductService sut;
    static ProductRepository mockProductRepository;

    @BeforeAll
    public static void testPrep(){
        mockProductRepository = mock(ProductRepository.class);
        sut = new ProductService(mockProductRepository);
    }

    @Test
    public void test_findAll_returnsAllProducts(){
        List<Product> products = new ArrayList<>();
        Product newProduct = new Product(1,
                "Headphones",
                "A nice pair of headphones",
                20.00,
                10,
                "https://i.insider.com/54eb437f6bb3f7697f85da71?width=1000&format=jpeg&auto=webp");

        products.add(newProduct);
        when(mockProductRepository.findAllActive()).thenReturn(products);

        List<Product> result = sut.findAll();
        for(Product p : result){
            Assertions.assertInstanceOf(Product.class, p);
        }

        verify(mockProductRepository, times(1)).findAllActive();
    }

    @Test
    public void test_findByKeyword_returnsListOfProducts_givenValidKeyword(){
        List<Product> products = new ArrayList<>();
        Product bag = new Product(3,
                "Shopping Bag",
                "A reusable shopping bag",
                2.50,
                20,
                "https://images.ctfassets.net/5gvckmvm9289/3BlDoZxSSjqAvv1jBJP7TH/65f9a95484117730ace42abf64e89572/Noissue-x-Creatsy-Tote-Bag-Mockup-Bundle-_4_-2.png");

        Product baseballCap = new Product( 4,
                "Baseball Cap",
                "A fancy cap for a fancy person",
                10.00,
                20,
                "https://d3o2e4jr3mxnm3.cloudfront.net/Rocket-Vintage-Chill-Cap_66374_1_lg.png");

        products.add(bag);
        products.add(baseballCap);

        when(mockProductRepository.findByKeyword(eq("ba"))).thenReturn(products);

        List<Product> result = sut.findByKeyword("ba");
        for(Product p : result){
            Assertions.assertInstanceOf(Product.class, p);
        }

        verify(mockProductRepository, times(1)).findByKeyword(eq("%ba%"));
    }


    @Test
    public void test_save_returnsNewProduct_givenValidProduct() {
        Product newProduct = new Product(777,
                "New Valid Product",
                "This new product is very expensive",
                99.99,
                12,
                "https://images.ctfassets.net/5gvckmvm9289/3BlDoZxSSjqAvv1jBJP7TH/65f9a95484117730ace42abf64e89572/Noissue-x-Creatsy-Tote-Bag-Mockup-Bundle-_4_-2.png");

        when(mockProductRepository.save(newProduct)).thenReturn(newProduct);
        newProduct.setId(2);
        Product returnedProduct = sut.save(newProduct);

        Assertions.assertInstanceOf(Product.class, returnedProduct);
        verify(mockProductRepository, times(1)).save(newProduct);
    }

    @Test
    public void test_saveAll_returnsNewProduct_givenValidProduct(){
        List<Product> products = new ArrayList<>();
        List<ProductInfo> productInfos = new ArrayList<>();
        Product bag = new Product(3,
                "Shopping Bag",
                "A reusable shopping bag",
                2.50,
                20,
                "https://images.ctfassets.net/5gvckmvm9289/3BlDoZxSSjqAvv1jBJP7TH/65f9a95484117730ace42abf64e89572/Noissue-x-Creatsy-Tote-Bag-Mockup-Bundle-_4_-2.png");

        Product baseballCap = new Product( 4,
                "Baseball Cap",
                "A fancy cap for a fancy person",
                10.00,
                20,
                "https://d3o2e4jr3mxnm3.cloudfront.net/Rocket-Vintage-Chill-Cap_66374_1_lg.png");

        products.add(bag);
        products.add(baseballCap);
        productInfos.add(new ProductInfo(3, 20));
        productInfos.add(new ProductInfo(4, 20));

        when(mockProductRepository.saveAll(products)).thenReturn(products);

        List<Product> returnedProducts = sut.saveAll(products, productInfos);

        for(Product p : returnedProducts) {
            Assertions.assertInstanceOf(Product.class, p);
        }

        verify(mockProductRepository, times(1)).saveAll(products);
    }


}
