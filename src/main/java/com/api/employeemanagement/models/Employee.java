package com.api.employeemanagement.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Details about an employee")
public class Employee {

    @Schema(description = "The unique ID of the employee", required = true)
    private int id;

    @Schema(description = "The name of the employee", required = true)
    private String nome;

    @Schema(description = "The designation of the employee", required = true)
    private String designacao;

    @Schema(description = "The wage of the employee", required = true)
    private double salario;

    @Schema(description = "The phone number of the employee", required = true)
    private String numeroTelefone;

    @Schema(description = "The address of the employee", required = true)
    private String endereco;
}
