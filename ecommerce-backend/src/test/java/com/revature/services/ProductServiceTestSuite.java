package com.revature.services;

import com.revature.dtos.ProductInfo;
import com.revature.models.Product;
import com.revature.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class ProductServiceTestSuite {

    public ProductService sut;
    public ProductRepository mockProductRepository;

    @BeforeEach
    public  void testPrep(){
        mockProductRepository = mock(ProductRepository.class);
        sut = new ProductService(mockProductRepository);
    }

    @Test
    public void test_findAll_returnsAllProducts(){
        List<Product> products = new ArrayList<>();
        Product newProduct = new Product(1,
                10,
                20.00,
                "A nice pair of headphones",
                "https://i.insider.com/54eb437f6bb3f7697f85da71?width=1000&format=jpeg&auto=webp",
                "Headphones",
                true);
        products.add(newProduct);
        when(mockProductRepository.findAllActive()).thenReturn(products);

        List<Product> result = sut.findAll();
        for(Product p : result){
            Assertions.assertInstanceOf(Product.class, p);
        }

        verify(mockProductRepository, times(1)).findAllActive();
        //verify needs method verified specified -- OR change before all to before each
    }

    @Test
    public void test_findById_returnsProduct_givenValidId(){
        Product newProduct = new Product(1,
                10,
                20.00,
                "A nice pair of headphones",
                "https://i.insider.com/54eb437f6bb3f7697f85da71?width=1000&format=jpeg&auto=webp",
                "Headphones",
                true);

        when(mockProductRepository.findActiveById(eq(1))).thenReturn(Optional.of(newProduct));

        Product returnedProduct = sut.findById(1).get();

        Assertions.assertInstanceOf(Product.class, returnedProduct);
        verify(mockProductRepository, times(1)).findActiveById(anyInt());
    }

    @Test
    public void test_findByKeyword_returnsListOfProducts_givenValidKeyword(){
        List<Product> products = new ArrayList<>();
        Product bag = new Product(3,
                20,
                2.50,
                "A reusable shopping bag",
                "https://images.ctfassets.net/5gvckmvm9289/3BlDoZxSSjqAvv1jBJP7TH/65f9a95484117730ace42abf64e89572/Noissue-x-Creatsy-Tote-Bag-Mockup-Bundle-_4_-2.png",
                "Shopping Bag",
                true);

        Product baseballCap = new Product( 4,
                20,
                10.00,
                "A fancy cap for a fancy person",
                "https://d3o2e4jr3mxnm3.cloudfront.net/Rocket-Vintage-Chill-Cap_66374_1_lg.png",
                "Baseball Cap",
                true);

        products.add(bag);
        products.add(baseballCap);

        when(mockProductRepository.findByKeyword(eq("ba"))).thenReturn(products);

        List<Product> result = sut.findByKeyword("ba");
        for(Product p : result){
            Assertions.assertInstanceOf(Product.class, p);
        }

        verify(mockProductRepository, times(1)).findByKeyword(anyString());
    }

    @Test
    public void test_save_returnsNewProduct_givenValidProduct(){
        Product newProduct = new Product(777,
                12,
                99.99,
                "This new product is very expensive",
                "https://images.ctfassets.net/5gvckmvm9289/3BlDoZxSSjqAvv1jBJP7TH/65f9a95484117730ace42abf64e89572/Noissue-x-Creatsy-Tote-Bag-Mockup-Bundle-_4_-2.png",
                "New Valid Product",
                true);

        when(mockProductRepository.save(newProduct)).thenReturn(newProduct);
        newProduct.setId(2);
        Product returnedProduct = sut.save(newProduct);

        Assertions.assertInstanceOf(Product.class, returnedProduct);
        verify(mockProductRepository, times(1)).save(any());
    }

    @Test
    public void test_saveAll_returnsNewProduct_givenValidProduct(){
        List<Product> products = new ArrayList<>();
        List<ProductInfo> productInfos = new ArrayList<>();
        Product bag = new Product(3,
                20,
                2.50,
                "A reusable shopping bag",
                "https://images.ctfassets.net/5gvckmvm9289/3BlDoZxSSjqAvv1jBJP7TH/65f9a95484117730ace42abf64e89572/Noissue-x-Creatsy-Tote-Bag-Mockup-Bundle-_4_-2.png",
                "Shopping Bag",
                true);

        Product baseballCap = new Product( 4,
                20,
                10.00,
                "A fancy cap for a fancy person",
                "https://d3o2e4jr3mxnm3.cloudfront.net/Rocket-Vintage-Chill-Cap_66374_1_lg.png",
                "Baseball Cap",
                true);

        products.add(bag);
        products.add(baseballCap);
        productInfos.add(new ProductInfo(3, 20));
        productInfos.add(new ProductInfo(4, 20));

        when(mockProductRepository.saveAll(products)).thenReturn(products);

        List<Product> returnedProducts = sut.saveAll(products, productInfos);

        for(Product p : returnedProducts){
            Assertions.assertInstanceOf(Product.class, p);
        }

        verify(mockProductRepository, times(1)).saveAll(any());
    }

    @Test
    public void test_delete_givenValidInt(){
        Product newProduct = new Product(1,
                10,
                20.00,
                "A nice pair of headphones",
                "https://i.insider.com/54eb437f6bb3f7697f85da71?width=1000&format=jpeg&auto=webp",
                "Headphones",
                true);

        when(mockProductRepository.findActiveById(eq(1))).thenReturn(Optional.of(newProduct));
        when(mockProductRepository.save(newProduct)).thenReturn(newProduct);

        sut.delete(1);

        verify(mockProductRepository, times(1)).findActiveById(anyInt());
        verify(mockProductRepository, times(1)).save(any());
    }
}
