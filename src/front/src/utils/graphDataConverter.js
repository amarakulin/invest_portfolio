const graphDataConverter = (data) => {
	if (data.lines === null)
		return data
	const len = data.lines[0].length;

	const getNewLineData = (line) => {
		const arr = new Array(len - line.length)
			.fill(null)
			.concat(...line.filter((_, i) => i !== 0));
		
		arr.unshift(line[0]);
		return arr;
	}

	data.lines = data.lines.map(line => {
		line = line.map(el => isNaN(parseFloat(el)) ? el : parseFloat(el));

		if (data.types[line[0]] !== 'line')
			return line;
		
		return getNewLineData(line);
	})
	return data;
}

export default graphDataConverter;