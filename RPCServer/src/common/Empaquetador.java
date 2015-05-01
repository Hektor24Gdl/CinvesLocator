package common;

public class Empaquetador {
	private final static int BYTE_LEN = 8;

	public static byte[] empaquetar( Object[] params, int[] paramsLength, int MAX_LENGTH){
		byte[] paquete = new byte[MAX_LENGTH];
		int offset = 0;
		for( int paramActual = 0;paramActual < params.length; paramActual++){
			Object actual = params[paramActual];
			int tamActual = paramsLength[paramActual];
			if( actual instanceof Integer){
				for( int bActual = 0; bActual<tamActual; bActual++ ){
					int corrimiento = tamActual - bActual - 1;
					paquete[ offset + bActual ] = 
							(byte)( (Integer)actual >> (BYTE_LEN*( corrimiento )) );
				}
			}else if( actual instanceof byte[] ){
				byte[] act = (byte[])actual;
				for( int i = 0; i<act.length && i <tamActual; i++ ){
					paquete[ offset + i ] = act[ i ];
				}												
			}else if( actual instanceof String){
				byte[] act = ((String)actual).getBytes();
				for( int i = 0; i<act.length && i<tamActual; i++ ){
					paquete[ offset + i ] = act[i];
				}																
			}
			offset+=tamActual;
		}
		return paquete;
	}

	public static short getShort( byte[] buffer, int offset ){
		final int SHOR_BYTES = 2;
		final int BYTE_LEN  = 8;
		short res = 0;
		for( int cnt=0;cnt<SHOR_BYTES;cnt++ ){
			for( int curBit = 0; curBit < BYTE_LEN; curBit++){
				if( (buffer[cnt + offset] & ( 1 << curBit)) != 0){
					res |= 1 << ((SHOR_BYTES-cnt-1)*BYTE_LEN + curBit);
				}
			}
		}
		return res;		
	}
	
	public static int getInt( byte[] buffer, int offset ){
		final int INT_BYTES = 4;
		final int BYTE_LEN  = 8;
		int res = 0;
		for( int cnt=0;cnt<INT_BYTES;cnt++ ){
			for( int curBit = 0; curBit < BYTE_LEN; curBit++){
				if( (buffer[cnt + offset] & ( 1 << curBit)) != 0){
					res |= 1 << ((INT_BYTES-cnt-1)*BYTE_LEN + curBit);
				}
			}
		}
		return res;
	}
	
	public static String getString( byte[] buffer, int offset, int tam ){
		return new String( buffer,offset,tam );
	}
	
	public static byte[] getByte( byte[] buffer, int offset, int tam ){
		byte[] res = new byte[tam];
		for( int i = 0; i < tam && i < buffer.length; i++){
			res[i] = buffer[offset+i];
		}
		return res;
	}
	public static byte[] getByteLimpio( byte[] buffer, int offset, int tam ){
		int buffLen = tam;
		for( int i = 0; i < tam && buffLen == tam; i++){
			if( buffer[ offset + i ] == 0 ){
				buffLen = i;
			}
		}
		byte[] res = new byte[buffLen];
		for( int i = 0; i < buffLen; i++){
			res[i] = buffer[offset+i];
		}
		return res;
	}
}