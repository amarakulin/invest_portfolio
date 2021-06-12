export const  getYRatio = (height, yMax, yMin) => {
	return (yMax - yMin) / height
}

export const  getXRatio = (width, length) => {
	return width / (length - 2)
}

export const toCoords = (y, i, xRatio, yRatio, DPI_HEIGHT, PADDING, yMin, offsetX) => {
	return [
		Math.floor((i - 1) * xRatio + offsetX),
		y !== null ?  Math.floor(DPI_HEIGHT - PADDING - ((y - yMin) /  yRatio)) : null
	];
}

export const renderLines = (ctx, yData, xRatio, yRatio, DPI_HEIGHT, PADDING, data, yMin, offsetX) => {
	const renderLine = (coords, color = '#000') => {
		ctx.beginPath();
		ctx.lineWidth = 4;
		ctx.strokeStyle = color;
		ctx.lineJoin = 'bevel';
		for (const [x, y] of coords) {
			if (y === null)
					continue;
			ctx.lineTo(x, y);
		}
		ctx.stroke();
		ctx.closePath();
	}


	yData.forEach(line => {
		const coords = line.map((y, i) => toCoords(y, i, xRatio, yRatio, DPI_HEIGHT, PADDING, yMin, offsetX)).filter((_, i) => i !== 0);
		
		renderLine(coords, data.color[line[0]]);
	})
}

export const calculateBounderies = ({lines, types}) => {
	let min;
	let max;

	lines.forEach((line) => {
		if (types[line[0]] !== 'line')
			return;

		if (typeof (max) !== 'number')
			max = line[1];
		if (typeof (min) !== 'number')
			min = line[1];

		if (min > line[1])
			min = line[1];
		if (max < line[1])
			max = line[1];

		for (let i = 2; i < line.length; i++) {
			if (min > line[i])
				min = line[i];
			if (max < line[i])
				max = line[i];
		}
	})
	return [min, max];
}

export const toDate = (timestamp) => {
	const addZero = (num) => num < 10 ? `0${num}` : num;

	const date = new Date(timestamp);

	return `${addZero(date.getDate())}.${addZero(date.getMonth() + 1)}.${date.getFullYear()}`
}

export const checkIsAllHidden = (hidden, total) => {
	return hidden.length === total.length - 1
}