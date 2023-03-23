import { ProductCard } from "./ProductCard"
import { render, screen } from "@testing-library/react"
import ReactDOM from 'react-dom'
import React from "react";

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