package com.jxt.jxtbank.role.controller;

import com.jxt.jxtbank.res.Response;
import com.jxt.jxtbank.role.entity.Role;
import com.jxt.jxtbank.role.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/roles")
@PreAuthorize("hasAuthority('ADMIN')")
public class RoleController {


    private final RoleService roleService;

    @PostMapping("/create")
    public ResponseEntity<Response<Role>> createRole(@RequestBody Role roleRequest){
        return ResponseEntity.ok(roleService.createRole(roleRequest));
    }
    @PutMapping("/update")
    public ResponseEntity<Response<Role>> updateRole(@RequestBody Role roleRequest){
        return ResponseEntity.ok(roleService.updateRole(roleRequest));
    }
    @GetMapping("/get-all")
    public ResponseEntity<Response<List<Role>>> getAllRole(@RequestBody Role roleRequest){
        return ResponseEntity.ok(roleService.getAllRole(roleRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<?>> deleteRole(@PathVariable Role role) {
        return ResponseEntity.ok(roleService.deleteRole(role));
    }


}


