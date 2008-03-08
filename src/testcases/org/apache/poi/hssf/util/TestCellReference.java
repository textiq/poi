/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */
        
package org.apache.poi.hssf.util;


import junit.framework.TestCase;


public final class TestCellReference extends TestCase {
    
    public void testAbsRef1(){
        CellReference cf = new CellReference("$B$5");
        confirmCell(cf, null, 4, 1, true, true, "$B$5");
    }
    
    public void  testAbsRef2(){
        CellReference cf = new CellReference(4,1,true,true);
        confirmCell(cf, null, 4, 1, true, true, "$B$5");
    }

    public void  testAbsRef3(){
        CellReference cf = new CellReference("B$5");
        confirmCell(cf, null, 4, 1, true, false, "B$5");
    }
    
    public void  testAbsRef4(){
        CellReference cf = new CellReference(4,1,true,false);
        confirmCell(cf, null, 4, 1, true, false, "B$5");
    }
    
    public void  testAbsRef5(){
        CellReference cf = new CellReference("$B5");
        confirmCell(cf, null, 4, 1, false, true, "$B5");
    }
    
    public void  testAbsRef6(){
        CellReference cf = new CellReference(4,1,false,true);
        confirmCell(cf, null, 4, 1, false, true, "$B5");
    }

    public void  testAbsRef7(){
        CellReference cf = new CellReference("B5");
        confirmCell(cf, null, 4, 1, false, false, "B5");
    }
    
    public void  testAbsRef8(){
        CellReference cf = new CellReference(4,1,false,false);
        confirmCell(cf, null, 4, 1, false, false, "B5");
    }
    
    public void testSpecialSheetNames() {
        CellReference cf;
        cf = new CellReference("'profit + loss'!A1");
        confirmCell(cf, "profit + loss", 0, 0, false, false, "'profit + loss'!A1");
        
        cf = new CellReference("'O''Brien''s Sales'!A1");
        confirmCell(cf, "O'Brien's Sales", 0, 0, false, false, "'O''Brien''s Sales'!A1");
        
        cf = new CellReference("'Amazing!'!A1");
        confirmCell(cf, "Amazing!", 0, 0, false, false, "'Amazing!'!A1");
    }

    
    /* package */ static void confirmCell(CellReference cf, String expSheetName, int expRow, 
            int expCol, boolean expIsRowAbs, boolean expIsColAbs, String expText) {
        
        assertEquals(expSheetName, cf.getSheetName());
        assertEquals("row index is wrong", expRow, cf.getRow());
        assertEquals("col index is wrong", expCol, cf.getCol());
        assertEquals("isRowAbsolute is wrong", expIsRowAbs, cf.isRowAbsolute());
        assertEquals("isColAbsolute is wrong", expIsColAbs, cf.isColAbsolute());
        assertEquals("text is wrong", expText, cf.formatAsString());
    }

    public static void main(String [] args) {
        System.out.println("Testing org.apache.poi.hssf.util.TestCellReference");
        junit.textui.TestRunner.run(TestCellReference.class);
    }
}
