/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2000-2017 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://oss.oracle.com/licenses/CDDL+GPL-1.1
 * or LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

/*
 * @(#)IllegalStateException.java	1.4 07/02/07
 */ 

package com.sun.messaging.jms;

import java.io.*;
import javax.jms.*;

import com.sun.messaging.jmq.jmsclient.logging.Loggable;

/**
 * <P> This exception is thrown when a method is
 *     invoked at an illegal or inappropriate time or if the provider is
 *     not in an appropriate state for the requested operation. For example,
 *     this exception must be thrown if <CODE>Session.commit</CODE> is
 *     called on a non-transacted session. This exception is also called when
 *     a domain inappropriate method is called, such as calling
 *     <CODE>TopicSession.CreateQueueBrowser</CODE>.
 **/

public class IllegalStateException extends javax.jms.IllegalStateException
             implements Loggable {

  private boolean isLogged = false;

  private Throwable cause = null;

  /** Constructs a <CODE>IllegalStateException</CODE> with the specified reason and
   *  error code.
   *
   *  @param  reason        a description of the exception
   *  @param  errorCode     a string specifying the vendor-specific
   *                        error code
   **/
  public
  IllegalStateException(String reason, String errorCode) {
    super(reason, errorCode);
  }

  /** Constructs a <CODE>IllegalStateException</CODE> with the specified reason and with
   *  the error code defaulting to null.
   *
   *  @param  reason        a description of the exception
   **/
  public
  IllegalStateException(String reason) {
    super(reason);
  }

  /** Constructs a <CODE>IllegalStateException</CODE> with the specified reason,
   *  error code, and a specified cause.
   *
   *  @param  reason        a description of the exception
   *  @param  errorCode     a string specifying the vendor-specific
   *                        error code
   *  @param  cause         the cause. A <tt>null</tt> value is permitted,
   *                        and indicates that the cause is non-existent
   *                        or unknown.
   **/
  public
  IllegalStateException(String reason, String errorCode, Throwable cause) {
    super(reason, errorCode);
    this.cause = cause;
    if (cause != null && cause instanceof java.lang.Exception) {
        setLinkedException((Exception)cause);
    }
  }

  /**
   *
   * <P>
   * If running under J2SE1.4 or above, this method will also
   * set the cause of the <CODE>IllegalStateException</CODE>.
   * When a backtrace of the <CODE>IllegalStateException</CODE> is printed
   * using {@link java.lang.Exception#printStackTrace printStackTrace}
   * using {@link java.lang.Throwable#printStackTrace printStackTrace}
   * a backtrace of the cause will also get printed.
   *
  **/
  public
  synchronized void setLinkedException(Exception ex) {
      super.setLinkedException(ex);
      try {
          initCause(ex);
      } catch (Throwable t) {

      }
  }

  /**
   *
   * <P>
   * If running under versions of the Java platform prior to J2SE1.4,
   * this method will also print the backtrace of the exception linked
   * to this <CODE>IllegalStateException</CODE> and obtained via
   * {@link javax.jms.JMSException#getLinkedException javax.jms.JMSException.getLinkedException()}
  **/
  public
  void printStackTrace() {
      this.printStackTrace(System.err);
  }

  /**
   * {@inheritDoc}
   * <P>
   * If running under versions of the Java platform prior to J2SE1.4,
   * this method will also print the backtrace of the exception linked
   * to this <CODE>IllegalStateException</CODE> and obtained via
   * {@link javax.jms.JMSException#getLinkedException javax.jms.JMSException.getLinkedException()}
  **/
  public
  void printStackTrace(PrintStream s) {
      Throwable cause;
      super.printStackTrace(s);
      try {
          getCause();
      } catch (Throwable t) {
	  if ( (cause = getLinkedException()) != null) {
              synchronized (s) {
                  s.print("Caused by: ");
              }
              cause.printStackTrace(s);
          }

      }
  }

  /**
   *
   * <P>
   * If running under versions of the Java platform prior to J2SE1.4,
   * this method will also print the backtrace of the exception linked
   * to this <CODE>IllegalStateException</CODE> and obtained via
   * {@link javax.jms.JMSException#getLinkedException}
  **/
  public
  void printStackTrace(PrintWriter s) {
      Throwable cause;
      super.printStackTrace(s);
      try {
          getCause();
      } catch (Throwable t) {
	  if ( (cause = getLinkedException()) != null) {
              synchronized (s) {
                  s.print("Caused by: "); // + cause.toString());
              }
              cause.printStackTrace(s);
          }

      }
  }

    /**
     * set state to true if this object is logged.
     * @param state boolean
     */
    public void setLogState (boolean state) {
        this.isLogged = state;
    }

    /**
     * get logging state of this object.
     * @return boolean true if this object is logged.
     */
    public boolean getLogState() {
        return this.isLogged;
    }

}
