export const getYRatio = (height, yMax, yMin) => {
	return (yMax - yMin) / height
}

export const getXRatio = (width, length) => {
	return width / (length - 2)
}

export const toCoords = (y, i, xRatio, yRatio, DPI_HEIGHT, PADDING, yMin, offsetX) => {
	return [
		Math.floor((i - 1) * xRatio + offsetX),
		y !== null ? Math.floor(DPI_HEIGHT - PADDING - ((y - yMin) / yRatio)) : null
	];
}

export const renderLines = (ctx, yData, xRatio, yRatio, DPI_HEIGHT, PADDING, data, yMin, offsetX) => {
	const xData = data.lines[0];

	const renderLine = (coords, color = '#000', startTime = 0) => {
		let iterator = 1;

		ctx.beginPath();
		ctx.lineWidth = 4;
		ctx.strokeStyle = color;
		ctx.lineJoin = 'bevel';
		for (const [x, y] of coords) {
			if (xData[iterator] < startTime) {
				iterator++;
				continue;
			}
			ctx.lineTo(x, y);
			iterator++;
		}
		ctx.stroke();
		ctx.closePath();
	}


	yData.forEach(line => {
		const coords = line.map((y, i) => toCoords(y, i, xRatio, yRatio, DPI_HEIGHT, PADDING, yMin, offsetX)).filter((_, i) => i !== 0);

		renderLine(coords, data.color[line[0]], data.purchaseDate[line[0]]);
	})
}

export const calculateBounderies = (lines) => {
	const yData = lines
		.map(line => line = line.filter((el, i) => el !== null && i !== 0))
		.filter((_, i) => i !== 0);
	let min = Math.min(...yData[0]);
	let max = Math.max(...yData[0]);

	yData.forEach((line) => {
		if (min > Math.min(...line))
			min = Math.min(...line);
		if (max < Math.max(...line))
			max = Math.max(...line);
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