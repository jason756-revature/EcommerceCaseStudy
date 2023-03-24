import { render, screen } from "@testing-library/react"
import ReactDOM from 'react-dom'
import { OrderCard } from "./OrderCard"

it('renders DisplayProduct without crashing', ()=>{
    const div = document.createElement('div')
    ReactDOM.render(<OrderCard
        key={1}
        order={{orderId: 1,
            paymentId: "CC1",
            orderDate: "2023-01-01",
            shipmentAddress: "123 Candy Way" }}
            ></OrderCard>, div)
})
