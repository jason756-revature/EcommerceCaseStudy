import { render, screen, waitFor } from '@testing-library/react';
//import App from './App';

import { apiGetAllUserOrders } from '../remote/e-commerce-api/orderService';
import { apiLogin } from '../remote/e-commerce-api/authService';
import { OrderCard } from '../components/orders/OrderCard';
import { DisplayProducts } from '../components/display-products/DisplayProducts';

test('grabs all orders for user', async () => {
    //expect.assertions(1);
    const user = await apiLogin("tets@gmail.com", "!test123");
    const orders = JSON.parse(`[
      {
        "orderId": 1000000,
        "userId": "1000000",
        "paymentId": "CC1",
        "orderDate": "2023-01-01",
        "shipmentAddress": "Example Address"
      }
  ]`)
    render(<OrderCard key={0} order={orders[0]}/>);
    const linkElement = screen.getByText("Order Header");
    expect(linkElement).toBeInTheDocument();
    
  });

test(`grabs no orders when not logged in`, async () =>{
    const orders = JSON.parse(`[
      {
        "orderId": 1000000,
        "userId": "1000000",
        "paymentId": "CC1",
        "orderDate": "2023-01-01",
        "shipmentAddress": "Example Address"
    }
  ]`)
  render(<DisplayProducts></DisplayProducts>);
  const linkElement = screen.queryByText("ORDERS")
  expect(linkElement).toBeNull();
})

