package edu.miu.assignment1.helpers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public ListMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <T> List<T> mapList(List<?> list, Class<T> clazz) {
        return list.stream()
                        .map(e -> modelMapper.map(e,clazz))
                        .collect(Collectors.toList());
    }
}
