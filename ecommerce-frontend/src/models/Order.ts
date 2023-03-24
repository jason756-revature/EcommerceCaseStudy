
export default class Order {
    orderId: number;
    paymentId: string;
    orderDate: string;
    shipmentAddress: string;

    constructor(
        orderId: number,
        paymentId: string,
        orderDate: string,
        shipmentAddress: string
    ){
        this.orderId = orderId;
        this.paymentId = paymentId;
        this.orderDate = orderDate;
        this.shipmentAddress = shipmentAddress;
    }
}