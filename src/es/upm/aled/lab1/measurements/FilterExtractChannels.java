package es.upm.aled.lab1.measurements;

/**
 * Filter that extracts the specified channels from an EEGModel.
 * 
 * @author mmiguel, rgarciacarmona
 *
 */
public class FilterExtractChannels implements Filter {

	private int[] validChannels;

	/**
	 * Builds the Filter. The use from an array of valid channels.
	 * 
	 * @param validChannels The channel numbers to be extracted, starting from 0.
	 */
	public FilterExtractChannels(int[] validChannels) {
		this.validChannels = validChannels;
		
	}

	@Override
	public EEGModel applyFilter(EEGModel eeg) {
		Measurement[] medidasOriginales = eeg.getMeasurements();					//array con todas las muestras original
		Measurement[] medidasFiltradas = new Measurement[medidasOriginales.length];	//array nuevo para las medidas filtradas
		for(int i = 0; i < medidasOriginales.length; i++) {
			Measurement original = medidasOriginales[i];
			
			float[] newChannels = new float[this.validChannels.length];
			for(int j = 0; j<validChannels.length; j++) {
				int indiceChannel = this.validChannels[j];
				newChannels[j] = original.getChannel(indiceChannel);
			}
			medidasFiltradas[i] = new Measurement(newChannels);
		}
		return new EEGModel(medidasFiltradas);
	}

}
