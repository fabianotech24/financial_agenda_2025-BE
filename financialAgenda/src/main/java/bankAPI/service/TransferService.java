package bankAPI.service;

import bankAPI.dto.TransferRequest;
import bankAPI.dto.TransferResponse;
import bankAPI.model.Transfer;
import bankAPI.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransferService {
    private final TransferRepository repository;
    private final FeeService feeService;

    public TransferResponse scheduleTransfer(TransferRequest request) {
        BigDecimal fee = feeService.calculateFee(request.amount(), request.transferDate());

        Transfer transfer = new Transfer();
        transfer.setSourceAccount(request.sourceAccount());
        transfer.setDestinationAccount(request.destinationAccount());
        transfer.setAmount(request.amount());
        transfer.setFee(fee);
        transfer.setTransferDate(request.transferDate());

        Transfer savedTransfer = repository.save(transfer);
        return convertToResponse(savedTransfer);
    }

    public List<TransferResponse> getAllTransfers() {
        return repository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private TransferResponse convertToResponse(Transfer transfer) {
        return new TransferResponse(
                transfer.getId(),
                transfer.getSourceAccount(),
                transfer.getDestinationAccount(),
                transfer.getAmount(),
                transfer.getFee(),
                transfer.getTransferDate(),
                transfer.getSchedulingDate()
        );
    }
}