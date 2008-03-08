/*
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package org.apache.poi.hssf.record.formula.eval;

import org.apache.poi.hssf.record.formula.Ptg;
import org.apache.poi.hssf.record.formula.SubtractPtg;

/**
 * @author Amol S. Deshmukh &lt; amolweb at ya hoo dot com &gt;
 *  
 */
public final class SubtractEval extends NumericOperationEval {

    private SubtractPtg delegate;

    private static final ValueEvalToNumericXlator NUM_XLATOR = 
        new ValueEvalToNumericXlator((short)
                ( ValueEvalToNumericXlator.BOOL_IS_PARSED 
                | ValueEvalToNumericXlator.REF_BOOL_IS_PARSED
                | ValueEvalToNumericXlator.STRING_IS_PARSED
                | ValueEvalToNumericXlator.REF_STRING_IS_PARSED
                ));

    public SubtractEval(Ptg ptg) {
        delegate = (SubtractPtg) ptg;
    }
    
    protected ValueEvalToNumericXlator getXlator() {
        return NUM_XLATOR;
    }

    public Eval evaluate(Eval[] args, int srcRow, short srcCol) {
    	if(args.length != 2) {
    		return ErrorEval.VALUE_INVALID;
    	}
        Eval retval = null;
        double d0 = 0;
        double d1 = 0;
        ValueEval ve = singleOperandEvaluate(args[0], srcRow, srcCol);
        if (ve instanceof NumericValueEval) {
            d0 = ((NumericValueEval) ve).getNumberValue();
        }
        else if (ve instanceof BlankEval) {
            // do nothing
        }
        else {
            retval = ErrorEval.VALUE_INVALID;
        }
        
        if (retval == null) { // no error yet
            ve = singleOperandEvaluate(args[1], srcRow, srcCol);
            if (ve instanceof NumericValueEval) {
                d1 = ((NumericValueEval) ve).getNumberValue();
            }
            else if (ve instanceof BlankEval) {
                // do nothing
            }
            else {
                retval = ErrorEval.VALUE_INVALID;
            }
        }
 
        if (retval == null) {
            retval = (Double.isNaN(d0) || Double.isNaN(d1)) 
                    ? (ValueEval) ErrorEval.VALUE_INVALID 
                    : new NumberEval(d0 - d1);
        }
        return retval;
    }

    public int getNumberOfOperands() {
        return delegate.getNumberOfOperands();
    }

    public int getType() {
        return delegate.getType();
    }
}
