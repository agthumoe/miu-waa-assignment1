package edu.miu.assignment.others;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomMapper extends ModelMapper {
    public <T> List<T> map(List<?> list, Class<T> clazz) {
        return list.stream().map(d -> this.map(d, clazz)).collect(Collectors.toList());
    }
}
