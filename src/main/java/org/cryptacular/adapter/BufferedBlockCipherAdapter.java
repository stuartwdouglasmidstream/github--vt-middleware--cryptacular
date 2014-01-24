/*
 * Licensed to Virginia Tech under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Virginia Tech licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License.  You may obtain a
 * copy of the License at the following location:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.cryptacular.adapter;

import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;

/**
 * Adapts a {@link BufferedBlockCipher}.
 *
 * @author Marvin S. Addison
 */
public class BufferedBlockCipherAdapter implements BlockCipherAdapter
{
  /** All methods delegate to this instance. */
  private final BufferedBlockCipher cipherDelegate;


  /**
   * Creates a new instance that delegates to the given cipher.
   *
   * @param  delegate  Adapted cipher.
   */
  public BufferedBlockCipherAdapter(final BufferedBlockCipher delegate)
  {
    cipherDelegate = delegate;
  }


  /** {@inheritDoc} */
  @Override
  public int getOutputSize(final int len)
  {
    return cipherDelegate.getOutputSize(len);
  }


  /** {@inheritDoc} */
  @Override
  public void init(final boolean forEncryption, final CipherParameters params)
  {
    cipherDelegate.init(forEncryption, params);
  }


  /** {@inheritDoc} */
  @Override
  public int processBytes(final byte[] in, final int inOff, final int len, final byte[] out, final int outOff)
  {
    return cipherDelegate.processBytes(in, inOff, len, out, outOff);
  }


  /** {@inheritDoc} */
  @Override
  public int doFinal(final byte[] out, final int outOff)
  {
    try {
      return cipherDelegate.doFinal(out, outOff);
    } catch (InvalidCipherTextException e) {
      throw new RuntimeException("Error finalizing cipher", e);
    }
  }


  /** {@inheritDoc} */
  @Override
  public void reset()
  {
    cipherDelegate.reset();
  }
}
