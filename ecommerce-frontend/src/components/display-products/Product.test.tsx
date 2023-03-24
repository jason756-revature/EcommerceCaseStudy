import { ProductCard } from "./ProductCard"
import { render, screen } from "@testing-library/react"
import { apiGetAllProducts } from "../../remote/e-commerce-api/productService";
import ReactDOM from 'react-dom'
import { DisplayProducts } from "./DisplayProducts";
import Product from "../../models/Product";
import React from "react";


const products = [
    {
        "id": 1000000,
        "quantity": 15,
        "price": 2,
        "description": "this product is  an apple",
        "image": "https://cdn.pixabay.com/photo/2014/02/01/17/28/apple-256261_960_720.jpg",
        "name": "apple",
        "active": true
    },
    {
        "id": 1000001,
        "quantity": 20,
        "price": 1,
        "description": "this product is  an orange",
        "image": "https://cdn.pixabay.com/photo/2017/12/29/16/34/fruit-3048001_960_720.jpg",
        "name": "orange",
        "active": true
    },
    {
        "id": 1000002,
        "quantity": 25,
        "price": 4,
        "description": "this product is  a pineapple",
        "image": "https://cdn.pixabay.com/photo/2015/02/14/18/10/pineapple-636562_960_720.jpg",
        "name": "pineapple",
        "active": true
    },
    {
        "id": 1000003,
        "quantity": 10,
        "price": 10.99,
        "description": "See and speak at the same time!",
        "image": "https://cdn.pixabay.com/photo/2017/10/30/23/34/lamp-2903830_960_720.jpg",
        "name": "Microphone Lamp",
        "active": true
    },
    {
        "id": 1000005,
        "quantity": 1,
        "price": 5.99,
        "description": "Keep ahead of fashion by taking these wherever you go!",
        "image": "https://cdn.pixabay.com/photo/2015/04/14/17/08/alien-722415_960_720.jpg",
        "name": "Shrek Crocs",
        "active": true
    }

]

// it('gets all products', async () => {
//     expect.assertions(1);
//     const response = await apiGetAllProducts();
//     expect(response.payload).toEqual(expect.arrayContaining(products))
// })


it('renders DisplayProduct without crashing', ()=>{
    const div = document.createElement('div')
    ReactDOM.render(<DisplayProducts></DisplayProducts>, div)
})



it('checks if response is a Product', async()=>{
    const response = await apiGetAllProducts()

    for(let i = 0; i < products.length; i++){
        const product = new Product(1000000, "test users",  10, "Amazing product!",  2.0, "https://cdn.pixabay.com/photo/2014/02/01/17/28/apple-256261_960_720.jpg")
        expect(response.payload[i]).toBeInstanceOf(Object)
    }
})

it('renders ProductCard without crashing', ()=>{
    const div = document.createElement('div')
    ReactDOM.render(<ProductCard key={1}
        product={{id: 1,
            name: "test users",
            quantity: 10,
            description: "Amazing Fruits",
            price: 2.0,
            image: "none"
        }}
        ></ProductCard>, div)
})