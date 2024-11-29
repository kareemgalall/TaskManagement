package banquemisr.challenge05.taskmanagement.Mapper.impl;

import banquemisr.challenge05.taskmanagement.Mapper.Mapper;
import banquemisr.challenge05.taskmanagement.domain.model.UserEntity;
import banquemisr.challenge05.taskmanagement.dto.UserDto;
import org.hibernate.annotations.Comment;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<UserEntity, UserDto> {
    ModelMapper modelMapper;
    public UserMapper(ModelMapper modelMapper) {}
    @Override
    public UserEntity mapTo(UserDto userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }

    @Override
    public UserDto mapFrom(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDto.class);
    }
}
