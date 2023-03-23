import React from 'react';
import { Route, Routes } from "react-router-dom";
import { Cart } from '../components/cart/Cart';
import Checkout from '../components/checkout/Checkout';
import { DisplayProducts } from "../components/display-products/DisplayProducts";
import { EditProductPage } from '../components/edit-products/EditProductPage';
import { EditProducts } from '../components/edit-products/EditProducts';
import { CreateProduct } from '../components/edit-products/CreateProduct';
import Login from '../components/login/Login';
import Register from '../components/register/Register';
import { Orders } from '../components/orders/Orders';
import UserProfile from '../components/user/UserProfile';
import { ProductDetailsPage } from '../components/display-products/ProductDetailsPage';
import ReviewPage from '../components/reviews/ReviewPage';



export const AppRoutes: React.FC<unknown> = () => (
  <Routes>
    <Route path="/" element={<DisplayProducts />} />
    <Route path="/login" element={<Login />} />
    <Route path="/register" element={<Register />} />
    <Route path="/products/:id" element={<ProductDetailsPage/>} />
    <Route path="/checkout" element={<Checkout />} />
    <Route path="/cart" element={<Cart />} />
    <Route path="/orders" element={<Orders />}></Route>
    <Route path="/admin/products/" element={<EditProducts />} />
    <Route path="/admin/products/:page" element={<EditProducts />} />
    <Route path="/admin/product/:id" element={<EditProductPage />} />
    <Route path="/admin/createproduct" element={<CreateProduct />} />
    <Route path="/admin/products" element={<EditProducts />} />
    <Route path="/userProfile" element={<UserProfile/>} />
    <Route path="/reviewpage/:id" element={<ReviewPage/>} />
  </Routes>
)