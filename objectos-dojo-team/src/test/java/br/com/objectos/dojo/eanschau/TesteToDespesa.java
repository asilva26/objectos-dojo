/*
 * TesteToDespesa.java criado em 05/07/2013
 * 
 * Propriedade de Objectos Fábrica de Software LTDA.
 * Reprodução parcial ou total proibida.
 */
package br.com.objectos.dojo.eanschau;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.google.common.base.Function;
import com.google.common.base.Objects;

/**
 * @author edenir.anschau@objectos.com.br (Edenir Norberto Anschau)
 */
@Test
public class TesteToDespesa {

  private final ToDespesa desp = new ToDespesaImpl();

  public void deve_gerar_despesa() {
    String[] entrada = { "mercado xyz", "5.5", "05/07/2013", "VARIAVEL", "Mercado" };

    Despesa contra = DespesasFalso.DESPESA_VAR;
    String prova = new DespesaToString().apply(contra);

    Despesa pojo = desp.of(entrada);
    String res = new DespesaToString().apply(pojo);

    assertThat(res, equalTo(prova));
  }

  private class DespesaToString implements Function<Despesa, String> {

    @Override
    public String apply(Despesa input) {
      return Objects.toStringHelper(input)
          .addValue(input.getDescricao())
          .addValue(input.getValor())
          .addValue(input.getData())
          .addValue(input.getTipo())
          .addValue(input.getCategoria().getNome())
          .toString();
    }

  }

}