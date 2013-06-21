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
package br.com.objectos.dojo.megasena;

import java.util.Iterator;

/**
 * @author edenir.anschau@objectos.com.br (Edenir Norberto Anschau)
 */
public class TxtIterator implements Iterator<String> {

  private String proximaLinha;

  public TxtIterator() {
    this.proximaLinha = "1;11/03/1996;04 05 30 33 41 52";
  }

  @Override
  public boolean hasNext() {
    return proximaLinha != null;
  }

  @Override
  public String next() {
    String resultado = proximaLinha;
    avancar();
    return resultado;
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }

  private void avancar() {
    proximaLinha = null;
  }

}