package vn.edu.leading.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import vn.edu.leading.shop.dto.OrderDetailDTO;
import vn.edu.leading.shop.dto.OrderNameDTO;
import vn.edu.leading.shop.models.*;
import vn.edu.leading.shop.repositories.*;

import java.util.ArrayList;
import java.util.List;


@Service

public class OrderServicelmpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final ShipperRepository shipperRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public OrderServicelmpl(OrderRepository orderRepository, CustomerRepository customerRepository, EmployeeRepository employeeRepository, ShipperRepository shipperRepository, OrderDetailRepository orderDetailRepository, ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.shipperRepository = shipperRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<OrderModel> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<OrderModel> search(String term) {
        return orderRepository.findByCustomerIdContaining(term);
    }

    @Override
    public OrderModel findById(Long id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public boolean update(OrderModel order) {
        OrderModel orderModel = orderRepository.findById(order.getId()).orElse(null);
        if (orderModel == null)
            return false;
        orderRepository.save(order);
        return true;
    }

    @Override
    public void save(OrderModel order) {
        orderRepository.save(order);
    }

    @Override
    public boolean delete(Long id) {
        OrderModel orderModel = orderRepository.findById(id).orElse(null);
        if(orderModel==null)
            return false;
        orderRepository.delete(orderModel);
        return true;
    }

    @Override
    public OrderNameDTO getNameCustomer(Long id){
        OrderModel orderModel = orderRepository.findById(id).get();
        CustomerModel customerModel = customerRepository.findById(orderModel.getCustomerId()).get();
        EmployeeModel employeeModel = employeeRepository.findById(orderModel.getEmployeeId()).get();
        ShipperModel shipperModel = shipperRepository.findById(orderModel.getShipperId()).get();
        OrderNameDTO orderNameDTO = new OrderNameDTO();
        orderNameDTO.setCustomerName(customerModel.getContactName());
        orderNameDTO.setFirstNameEmployee(employeeModel.getFirstName());
        orderNameDTO.setShipperName(shipperModel.getShipperName());
        return orderNameDTO;
    }

    @Override
    public List<OrderDetailDTO> listProduct(Long id) {
        List<OrderDetailDTO> list = new ArrayList<>();
        List<OrderDetailModel> orderDetails = orderDetailRepository.findAllByOrderId(id);
        for(OrderDetailModel detail : orderDetails){
            OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
            ProductModel productModel = productRepository.findById(detail.getProductId()).get();
            orderDetailDTO.setProductName(productModel.getProductName());
            orderDetailDTO.setPrice(productModel.getPrice());
            orderDetailDTO.setQuantity(detail.getQuantity());
            orderDetailDTO.setSum(productModel.getPrice()*detail.getQuantity());
            list.add(orderDetailDTO);
        }
        return list;
    }
}
