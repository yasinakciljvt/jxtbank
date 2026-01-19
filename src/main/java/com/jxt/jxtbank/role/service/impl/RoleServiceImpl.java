package com.jxt.jxtbank.role.service.impl;

import com.jxt.jxtbank.exceptions.BadRequestException;
import com.jxt.jxtbank.exceptions.NotFoundException;
import com.jxt.jxtbank.res.Response;
import com.jxt.jxtbank.role.entity.Role;
import com.jxt.jxtbank.role.repository.RoleRepository;
import com.jxt.jxtbank.role.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Response<Role> createRole(Role roleRequest) {

        if(roleRepository.findByName(roleRequest.getName()).isPresent()){
            throw new BadRequestException("Role already exists");

        }

       Role saveRole= roleRepository.save(roleRequest);
        return Response.<Role>builder().statusCode(HttpStatus.OK.value())
                .message("Role saved")
                .data(saveRole).build();

    }

    @Override
    public Response<Role> updateRole(Role roleRequest) {
        Role role = roleRepository.findById(roleRequest.getId())
                .orElseThrow(() -> new NotFoundException("Role not found"));
        role.setName(roleRequest.getName());
        Role updatedRole = roleRepository.save(role);
        return Response.<Role>builder().statusCode(HttpStatus.OK.value())
                .message("Role updated")
                .data(updatedRole).build();


    }

    @Override
    public Response<List<Role>> getAllRole(Role roleRequest) {
        List<Role> roles = roleRepository.findAll();
        return Response.<List<Role>>builder()
                .message("Roles getting")
                .data(roles).build();
    }

    @Override
    public Response<?> deleteRole(Role roleRequest) {
        if (!roleRepository.existsById(roleRequest.getId())) {
            throw new NotFoundException("Role not found");
        }
            roleRepository.deleteById(roleRequest.getId());

            return Response.builder()
                    .statusCode(HttpStatus.OK.value())
                    .message("Role deleted")
                    .build();

    }
}
