package com.company.app.services;

import java.util.List;
import java.util.Map;

public interface DataLoader<T> {
    public List<T> loadData();
}
