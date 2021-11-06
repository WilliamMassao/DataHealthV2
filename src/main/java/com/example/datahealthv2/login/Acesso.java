package com.example.datahealthv2.login;

import java.sql.SQLException;
import com.example.datahealthv2.conexao.DAO.DAO;
import com.example.datahealthv2.conexao.DAO.usuario.UsuarioDAOFactory;
import com.example.datahealthv2.model.Usuario;

public class Acesso {
    private boolean validaSenha(String senhaRepositorio, String senhaDigitada) {
        return (senhaRepositorio.equals(senhaDigitada));
    }

    public boolean validaUsuario(Usuario user) throws SQLException, ClassNotFoundException {
        boolean retorno = false;

        DAO dao = UsuarioDAOFactory.factory(user);

        Usuario usuario = (Usuario)dao.localiza(user.getCpf());
        if (usuario != null) {
            retorno = validaSenha(usuario.getSenha(), user.getSenha());
        }
        return retorno;
    }
}
