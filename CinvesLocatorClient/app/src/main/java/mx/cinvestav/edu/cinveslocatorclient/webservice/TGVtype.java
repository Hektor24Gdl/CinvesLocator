package mx.cinvestav.edu.cinveslocatorclient.webservice;

//----------------------------------------------------
//
// Generated by www.easywsdl.com
// Version: 4.1.5.5
//
// Created by Quasar Development at 03-05-2015
//
//---------------------------------------------------


import java.util.Hashtable;
import org.ksoap2.serialization.*;

public class TGVtype extends AttributeContainer implements KvmSerializable
{
    
    public Integer idtype;
    
    public String type;

    public TGVtype ()
    {
    }

    public TGVtype (java.lang.Object paramObj,TGVExtendedSoapSerializationEnvelope __envelope)
    {
	    
	    if (paramObj == null)
            return;
        AttributeContainer inObj=(AttributeContainer)paramObj;


        SoapObject soapObject=(SoapObject)inObj;  
        if (soapObject.hasProperty("idtype"))
        {	
	        java.lang.Object obj = soapObject.getProperty("idtype");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class))
            {
                SoapPrimitive j =(SoapPrimitive) obj;
                if(j.toString()!=null)
                {
                    this.idtype = Integer.parseInt(j.toString());
                }
	        }
	        else if (obj!= null && obj instanceof Integer){
                this.idtype = (Integer)obj;
            }    
        }
        if (soapObject.hasProperty("type"))
        {	
	        java.lang.Object obj = soapObject.getProperty("type");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class))
            {
                SoapPrimitive j =(SoapPrimitive) obj;
                if(j.toString()!=null)
                {
                    this.type = j.toString();
                }
	        }
	        else if (obj!= null && obj instanceof String){
                this.type = (String)obj;
            }    
        }


    }

    @Override
    public java.lang.Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if(propertyIndex==0)
        {
            return this.idtype!=null?this.idtype:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==1)
        {
            return this.type!=null?this.type:SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 2;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info)
    {
        if(propertyIndex==0)
        {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "idtype";
            info.namespace= "";
        }
        if(propertyIndex==1)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "type";
            info.namespace= "";
        }
    }
    
    @Override
    public void setProperty(int arg0, java.lang.Object arg1)
    {
    }

    @Override
    public String getInnerText() {
        return null;
    }

    @Override
    public void setInnerText(String s) {

    }
}
