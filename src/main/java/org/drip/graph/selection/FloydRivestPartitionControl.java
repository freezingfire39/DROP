
package org.drip.graph.selection;

/*
 * -*- mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 */

/*!
 * Copyright (C) 2020 Lakshmi Krishnamurthy
 * 
 *  This file is part of DROP, an open-source library targeting analytics/risk, transaction cost analytics,
 *  	asset liability management analytics, capital, exposure, and margin analytics, valuation adjustment
 *  	analytics, and portfolio construction analytics within and across fixed income, credit, commodity,
 *  	equity, FX, and structured products. It also includes auxiliary libraries for algorithm support,
 *  	numerical analysis, numerical optimization, spline builder, model validation, statistical learning,
 *  	and computational support.
 *  
 *  	https://lakshmidrip.github.io/DROP/
 *  
 *  DROP is composed of three modules:
 *  
 *  - DROP Product Core - https://lakshmidrip.github.io/DROP-Product-Core/
 *  - DROP Portfolio Core - https://lakshmidrip.github.io/DROP-Portfolio-Core/
 *  - DROP Computational Core - https://lakshmidrip.github.io/DROP-Computational-Core/
 * 
 * 	DROP Product Core implements libraries for the following:
 * 	- Fixed Income Analytics
 * 	- Loan Analytics
 * 	- Transaction Cost Analytics
 * 
 * 	DROP Portfolio Core implements libraries for the following:
 * 	- Asset Allocation Analytics
 *  - Asset Liability Management Analytics
 * 	- Capital Estimation Analytics
 * 	- Exposure Analytics
 * 	- Margin Analytics
 * 	- XVA Analytics
 * 
 * 	DROP Computational Core implements libraries for the following:
 * 	- Algorithm Support
 * 	- Computation Support
 * 	- Function Analysis
 *  - Model Validation
 * 	- Numerical Analysis
 * 	- Numerical Optimizer
 * 	- Spline Builder
 *  - Statistical Learning
 * 
 * 	Documentation for DROP is Spread Over:
 * 
 * 	- Main                     => https://lakshmidrip.github.io/DROP/
 * 	- Wiki                     => https://github.com/lakshmiDRIP/DROP/wiki
 * 	- GitHub                   => https://github.com/lakshmiDRIP/DROP
 * 	- Repo Layout Taxonomy     => https://github.com/lakshmiDRIP/DROP/blob/master/Taxonomy.md
 * 	- Javadoc                  => https://lakshmidrip.github.io/DROP/Javadoc/index.html
 * 	- Technical Specifications => https://github.com/lakshmiDRIP/DROP/tree/master/Docs/Internal
 * 	- Release Versions         => https://lakshmidrip.github.io/DROP/version.html
 * 	- Community Credits        => https://lakshmidrip.github.io/DROP/credits.html
 * 	- Issues Catalog           => https://github.com/lakshmiDRIP/DROP/issues
 * 	- JUnit                    => https://lakshmidrip.github.io/DROP/junit/index.html
 * 	- Jacoco                   => https://lakshmidrip.github.io/DROP/jacoco/index.html
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *   	you may not use this file except in compliance with the License.
 *   
 *  You may obtain a copy of the License at
 *  	http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  	distributed under the License is distributed on an "AS IS" BASIS,
 *  	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  
 *  See the License for the specific language governing permissions and
 *  	limitations under the License.
 */

/**
 * <i>FloydRivestPartitionControl</i> implements the Control Parameters for the Floyd-Rivest Selection
 * 	Algorithm. The References are:
 * 
 * <br><br>
 *  <ul>
 *  	<li>
 *  		Floyd, R. W., and R. L. Rivest (1975): Expected Time Bounds for Selection <i>Communications of
 *  			the ACM</i> <b>18 (3)</b> 165-172
 *  	</li>
 *  	<li>
 *  		Floyd, R. W., and R. L. Rivest (1975): The Algorithm SELECT � for finding the i<sup>th</sup>
 *  			smallest of n Elements <i>Communications of the ACM</i> <b>18 (3)</b> 173
 *  	</li>
 *  	<li>
 *  		Hoare, C. A. R. (1961): Algorithm 65: Find <i>Communications of the ACM</i> <b>4 (1)</b> 321-322
 *  	</li>
 *  	<li>
 *  		Wikipedia (2019): Floyd-Rivest Algorithm
 *  			https://en.wikipedia.org/wiki/Floyd%E2%80%93Rivest_algorithm
 *  	</li>
 *  	<li>
 *  		Wikipedia (2019): Quickselect https://en.wikipedia.org/wiki/Quickselect
 *  	</li>
 *  </ul>
 *
 * <br><br>
 *  <ul>
 *		<li><b>Module </b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/ComputationalCore.md">Computational Core Module</a></li>
 *		<li><b>Library</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/GraphAlgorithmLibrary.md">Graph Algorithm Library</a></li>
 *		<li><b>Project</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/graph/README.md">Graph Optimization and Tree Construction Algorithms</a></li>
 *		<li><b>Package</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/graph/selection/README.md">k<sup>th</sup> Order Statistics Selection Scheme</a></li>
 *  </ul>
 * <br><br>
 *
 * @author Lakshmi Krishnamurthy
 */

public class FloydRivestPartitionControl
{

	/**
	 * The Floyd Rivest Algorithm 489 Width Limit
	 */

	public static final int ALGORITHM_489_WIDTH_LIMIT = 600;

	/**
	 * The Floyd Rivest Algorithm 489 Shrinkage Factor
	 */

	public static final double ALGORITHM_489_SHRINKAGE = 0.5;

	private int _widthLimit = -1;
	private double _shrinkage = java.lang.Double.NaN;

	/**
	 * Retrieve the Algorithm #489 Instance of FloydRivestPartitionControl
	 * 
	 * @return Algorithm #489 Instance of FloydRivestPartitionControl
	 */

	public static final FloydRivestPartitionControl Algorithm489()
	{
		try
		{
			return new FloydRivestPartitionControl (
				ALGORITHM_489_WIDTH_LIMIT,
				ALGORITHM_489_SHRINKAGE
			);
		}
		catch (java.lang.Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}
	/**
	 * FloydRivestPartitionControl Constructor
	 * 
	 * @param widthLimit Width Limit
	 * @param shrinkage Shrinkage Factor
	 * 
	 * @throws java.lang.Exception Thrown if the Inputs are Invalid
	 */

	public FloydRivestPartitionControl (
		final int widthLimit,
		final double shrinkage)
		throws java.lang.Exception
	{
		if (1 >= (_widthLimit = widthLimit) ||
			!org.drip.numerical.common.NumberUtil.IsValid (
				_shrinkage = shrinkage
			) || 1. <= _shrinkage
		)
		{
			throw new java.lang.Exception (
				"FloydRivestPartitionControl Constructor => Invalid Inputs"
			);
		}
	}

	/**
	 * Retrieve the Limiting Width
	 * 
	 * @return The Limiting Width
	 */

	public int widthLimit()
	{
		return _widthLimit;
	}

	/**
	 * Retrieve the Shrinkage
	 * 
	 * @return The Shrinkage
	 */

	public double shrinkage()
	{
		return _shrinkage;
	}
}
