/*
 * Copyright 2013 Objectos, Fábrica de Software LTDA.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package br.com.objectos.dojo.asilva;

import org.joda.time.LocalDate;

/**
 * @author anderson.silva@objectos.com.br (Anderson Amorim Silva)
 */
public class MegaSenaPojo implements MegaSena {

  private final int numeroConcurso;
  private final LocalDate dataSorteio;
  private final String resultado;

  public MegaSenaPojo(Construtor construtor) {
    numeroConcurso = construtor.getNumeroConcurso();
    dataSorteio = construtor.getDataSorteio();
    resultado = construtor.getResultado();
  }

  @Override
  public int getNumeroConcurso() {
    return numeroConcurso;
  }

  @Override
  public LocalDate getDataSorteio() {
    return dataSorteio;
  }

  @Override
  public String getResultado() {
    return resultado;
  }

}