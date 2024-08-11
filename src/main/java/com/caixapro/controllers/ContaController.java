package com.caixapro.controllers;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.caixapro.factories.ContaCorrenteUsuarioBronzeFactory;
import com.caixapro.factories.ContaCorrenteUsuarioOuroFactory;
import com.caixapro.factories.ContaCorrenteUsuarioPrataFactory;
import com.caixapro.factories.ContaPoupancaUsuarioBronzeFactory;
import com.caixapro.factories.ContaPoupancaUsuarioOuroFactory;
import com.caixapro.factories.ContaPoupancaUsuarioPrataFactory;
import com.caixapro.factories.UsuarioContaFactory;
import com.caixapro.models.Conta;
import com.caixapro.services.ContaService;

@Controller
public class ContaController {
	
	@Autowired
    private ContaService contaService;
	
    @GetMapping("/")
    public String index() {
        return "home";
    }
    
    @GetMapping("/home")
    public String home() {
        return "home";
    }
		
	@GetMapping("/account")
    public String account(@RequestParam Long accountId, Model model) {
        Conta conta = contaService.getContaById(accountId);
        model.addAttribute("account", conta);
        return "account";
    }
	
	@GetMapping("/new_account")
    public String showNovaContaForm(Model model) {
        model.addAttribute("tiposConta", Arrays.asList("Poupanca", "Corrente"));
        model.addAttribute("tiposUsuario", Arrays.asList("Ouro", "Prata", "Bronze"));
        return "newAccount";
    }
	
	@PostMapping("/new_account")
    public String criarConta(@RequestParam String tipoConta, @RequestParam String tipoUsuario, Model model) {
        UsuarioContaFactory factory = getFactory(tipoConta, tipoUsuario);
        Conta conta = contaService.criarContaComUsuario(factory);
        model.addAttribute("conta", conta);
        return "redirect:/account?accountId=" + conta.getId();
    }
	
    @GetMapping("/deposit")
    public String showDepositPage(@RequestParam Long accountId, Model model) {
        model.addAttribute("accountId", accountId);
        return "deposit";
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam Long accountId, @RequestParam double amount) {
        contaService.depositar(accountId, amount);
        return "redirect:/account?accountId=" + accountId;
    }
	
    @GetMapping("/withdraw")
    public String showWithdrawPage(@RequestParam Long accountId, Model model) {
        model.addAttribute("accountId", accountId);
        return "withdraw";
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam Long accountId, @RequestParam double amount) {
        contaService.sacar(accountId, amount);
        return "redirect:/account?accountId=" + accountId;
    }
		
    @GetMapping("/transfer")
    public String showTransferPage(@RequestParam Long accountId, Model model) {
        model.addAttribute("accountId", accountId);
        return "transfer";
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam Long fromAccountId, @RequestParam Long toAccountId, @RequestParam double amount) {
        contaService.transferir(fromAccountId, toAccountId, amount);
        return "redirect:/account?accountId=" + fromAccountId;
    }
	
    @GetMapping("/statement")
    public String statement(@RequestParam Long accountId, Model model) {
        Conta conta = contaService.getContaById(accountId);
        model.addAttribute("account", conta);
        model.addAttribute("transactions", contaService.emitirExtrato(accountId));
        return "statement";
    }
	
	private UsuarioContaFactory getFactory(String tipoConta, String tipoUsuario) {
        switch (tipoConta) {
            case "Poupanca":
                switch (tipoUsuario) {
                    case "Ouro": return new ContaPoupancaUsuarioOuroFactory();
                    case "Prata": return new ContaPoupancaUsuarioPrataFactory();
                    case "Bronze": return new ContaPoupancaUsuarioBronzeFactory();
                }
                break;
            case "Corrente":
                switch (tipoUsuario) {
                    case "Ouro": return new ContaCorrenteUsuarioOuroFactory();
                    case "Prata": return new ContaCorrenteUsuarioPrataFactory();
                    case "Bronze": return new ContaCorrenteUsuarioBronzeFactory();
                }
                break;
        }
        throw new IllegalArgumentException("Tipo de conta ou usuário inválido");
    }

}


