import { render, screen } from "@testing-library/react"
import { apiGetProductByKeyword } from "../../remote/e-commerce-api/productService"
import { ProductCard } from "./ProductCard"

const shrek = {
    id: 1000005,
    quantity: 1,
    price: 5.99,
    description: "Keep ahead of fashion by taking these wherever you go!",
    image: "https://cdn.pixabay.com/photo/2015/04/14/17/08/alien-722415_960_720.jpg",
    name: "Shrek Crocs",
    active: true
}

it('searches for shrek',async () => {
    // Search for shrek
    const product = await apiGetProductByKeyword("shrek")

    // Render product
    render(<ProductCard product={shrek} key={shrek.id}></ProductCard>)

    // Check if product was properly rendered
    const linkElement = screen.getByText(product.payload[0].name)
    expect(linkElement).toBeInTheDocument()
})

it('works with searchbar',async () => {
    expect.assertions(1);
    return apiGetProductByKeyword("shrek").then(data => expect(data.payload[0]).toEqual(shrek));
})