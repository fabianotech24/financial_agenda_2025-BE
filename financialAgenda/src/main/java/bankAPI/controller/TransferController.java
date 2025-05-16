package bankAPI.controller;

import bankAPI.dto.TransferRequest;
import bankAPI.dto.TransferResponse;
import bankAPI.service.TransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200") //Enable CORS in dev env.
@Validated
@Tag(name = "Transferências Bancárias", description = "Operações para agendamento e consulta de transferências")
public class TransferController {
    private final TransferService service;

    @Operation(description = "Agendar transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "[SUCCESS]-Set transfer operations"),
            @ApiResponse(responseCode = "201", description = "[SUCCESS]-Transfer created"),
            @ApiResponse(responseCode = "400", description = "[ERROR]-Params bad request"),
            @ApiResponse(responseCode = "500", description = "[ERROR]-Internal Server Error"),
    })
    @PostMapping(value="/scheduleTransfer", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransferResponse> scheduleTransfer(@Valid @RequestBody TransferRequest request) {
        return ResponseEntity.ok(service.scheduleTransfer(request));
    }

    @Operation(description = "List all transfers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "[SUCCESS]-Get all transfers"),
            @ApiResponse(responseCode = "400", description = "[ERROR]-Params bad request"),
            @ApiResponse(responseCode = "500", description = "[ERROR]-Internal Server Error"),
    })
    @GetMapping(value="/allTransfers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TransferResponse>> getAllTransfers() {
        return ResponseEntity.ok(service.getAllTransfers());
    }
}