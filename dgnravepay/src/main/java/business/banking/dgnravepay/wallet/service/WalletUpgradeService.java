package business.banking.dgnravepay.wallet.service;

import business.banking.dgnravepay.wallet.client.WalletUpgradeClient;
import business.banking.dgnravepay.wallet.dto.WalletUpgradeRequest;
import business.banking.dgnravepay.wallet.dto.WalletUpgradeResponseDto;
import org.springframework.stereotype.Service;

@Service
public class WalletUpgradeService {

    private final WalletUpgradeClient walletUpgradeClient;

    public WalletUpgradeService(WalletUpgradeClient walletUpgradeClient) {
        this.walletUpgradeClient = walletUpgradeClient;
    }

    public WalletUpgradeResponseDto upgradeWallet(
            WalletUpgradeRequest request,
            String email
    ) {
        return walletUpgradeClient.walletUpgrade(request, email);
    }
}
