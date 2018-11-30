package com.eappcat.mail.sender.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "system_parameter")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemParameter {
    @Id
    private String profile;
    private String config;
}
