import axios from 'axios';

const baseUrl = 'http://localhost:8082/orders'; 

// Placing order
export const placeOrder = async (orderData) => {
  try {
    const response = await axios.post(baseUrl, orderData);
    return response.data;
  } catch (error) {
    console.error('Error placing order:', error);
  }
};

// Get a specific order
export const getOrder = async (orderId) => {
  try {
    const response = await axios.get(`${baseUrl}/${orderId}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching order:', error);
  }
};

// Getting all orders
export const getAllOrders = async () => {
  try {
    const response = await axios.get(baseUrl);
    return response.data;
  } catch (error) {
    console.error('Error fetching orders:', error);
  }
};

// Updating order status
export const updateOrderStatus = async (orderId, newStatus) => {
  try {
    const response = await axios.put(`${baseUrl}/${orderId}`, { status: newStatus });
    return response.data;
  } catch (error) {
    console.error('Error updating order status:', error);
  }
};

// Deleting an order
export const deleteOrder = async (orderId) => {
  try {
    await axios.delete(`${baseUrl}/${orderId}`);
  } catch (error) {
    console.error('Error deleting order:', error);
  }
};
