package com.agibank.corehub.controller;

import com.agibank.corehub.dao.SuporteDAO;
import com.agibank.corehub.beans.Suporte;

import java.sql.SQLException;
import java.util.ArrayList;

public class SuporteController {
    private final SuporteDAO suporteDAO = new SuporteDAO();

    public SuporteController() throws SQLException {
    }

    public void adicionarChamado(int id_usuario, String descricao){
        Suporte suporte = new Suporte(id_usuario,descricao);
        try{
            suporteDAO.inserirSuporte(id_usuario,descricao);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public Suporte buscarChamadoPorId(int id_chamado) {
        Suporte suporte = null;

        try {
            suporte = suporteDAO.buscarChamadoPorId(id_chamado);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return suporte;
    }

    public ArrayList<Suporte> buscarChamadosIdUsuario(int id_usuario){
        ArrayList<Suporte> suportes = new ArrayList<>();
        try {
            suportes = suporteDAO.buscarChamadoPorIdUsuario(id_usuario);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return suportes;
    }

    public void exibirChamadosPorUsuario(int id_usuario){
        ArrayList<Suporte> suportes = buscarChamadosIdUsuario(id_usuario);

        for (Suporte suporte : suportes) {
            System.out.print(suporte.getDescricao() + " || ");

            if (suporte.getResolucao() != null) System.out.println(suporte.getResolucao());
            else System.out.println("Aguardando resposta");
        }

    }

    public void atenderChamado(int id_chamado, int id_funcionario){
        Suporte suporte = buscarChamadoPorId(id_chamado);
        if(suporte!=null){

            try{
                suporteDAO.inserirAtendenteSuporte(id_chamado,id_funcionario);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            suporte.setidFuncionario(id_funcionario);
        }
    }

    public void listarChamados(){
        try{
            suporteDAO.listarChamados();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void listarChamadosPendentes(){
        try{
            suporteDAO.listarChamadosPendentes();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listarChamadosAtendidos(){
        try{
            suporteDAO.listarChamadosAtendidos();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean verificarAutoriaChamado(int id_chamado, int id_usuario){
        Suporte suporte = new Suporte();
        try{
            suporte = suporteDAO.verificarAtendenteChamado(id_chamado,id_usuario);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return suporte != null;
    }

    public void resolverChamado(int id_suporte, String resolucao, int id_usuario){
        if(verificarAutoriaChamado(id_suporte,id_usuario)) {
            try{
                suporteDAO.resolverChamado(id_suporte,resolucao);
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }else System.out.println("Esse chamado não está sobre sua autoria!");
    }
}
