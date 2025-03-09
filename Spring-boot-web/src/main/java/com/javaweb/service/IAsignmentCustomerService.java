package com.javaweb.service;

import java.util.List;

public interface IAsignmentCustomerService {
    void assignCustomerToStaff(Long CustomerId, List<Long> staffIds);
}
